package com.example.taskmanagmentsystem.services;

import com.example.taskmanagmentsystem.dto.UserDto;
import com.example.taskmanagmentsystem.entities.User;
import com.example.taskmanagmentsystem.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerUser(User user) {
        System.out.println("rejestruje");
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("Set password " + passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public User loginUser(String login, String rawPassword) {
        System.out.println("Próba logowania: " + login);
        User user = userRepository.findByLogin(login);

        if (user == null) {
            System.out.println("Użytkownik nie istnieje");
            return null;
        }

        System.out.println("Hash z bazy: " + user.getPassword());
        System.out.println("Czy hasło się zgadza? " +
                passwordEncoder.matches(rawPassword, user.getPassword()));

        return passwordEncoder.matches(rawPassword, user.getPassword()) ? user : null;
    }


    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public boolean doesUserExist(String login) {
        return userRepository.existsByLogin(login);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }





    public UserDto updateUser(Long id, UserDto userDto) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    updateEntityFromDto(userDto, existingUser);
                    User updatedUser = userRepository.save(existingUser);
                    return convertToDto(updatedUser);
                })
                .orElse(null);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Metody pomocnicze do konwersji
    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    private User convertToEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());

        return user;
    }

    private void updateEntityFromDto(UserDto dto, User user) {
        if (dto.getLogin() != null) {
            user.setLogin(dto.getLogin());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }

    }

    // Metoda do użytku wewnętrznego/w innych serwisach
    public Optional<User> findEntityById(Long id) {
        return userRepository.findById(id);
    }





}