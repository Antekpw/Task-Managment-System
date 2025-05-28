package com.example.taskmanagmentsystem.controllers;
//
//
//import com.example.taskmanagmentsystem.entities.Project;
//import com.example.taskmanagmentsystem.entities.User;
//import com.example.taskmanagmentsystem.services.UserService;
//import com.example.taskmanagmentsystem.utils.GeneralService;
//import com.example.taskmanagmentsystem.utils.JwtUtils;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final JwtUtils jwtUtils;
//    private final UserService userService;
//
//    public AuthController(JwtUtils jwtUtils, UserService userService) {
//        this.jwtUtils = jwtUtils;
//        this.userService = userService;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws Exception {
//        // 1. Sprawdź, czy użytkownik istnieje w bazie
//        User user = userService.findByLogin(request.username());
//        if (user == null) {
//            System.out.println("User not found");
//        }
//        // 2. Zweryfikuj hasło (np. za pomocą BCrypt)
//        if (user != null && userService.isPasswordValid(user, request.password())) {
//            String token = jwtUtils.generateToken(user.getLogin()); // Generuj JWT
//            return ResponseEntity.ok(token);
//        }
//
//        // 3. Jeśli dane są niepoprawne, zwróć błąd 401
//        return ResponseEntity.status(401).body("Invalid credentials");
//    }
//
//    public record LoginRequest(String username, String password) {}
//}
import com.example.taskmanagmentsystem.entities.User;
import com.example.taskmanagmentsystem.services.UserService;
import com.example.taskmanagmentsystem.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public AuthController(JwtUtils jwtUtils, UserService userService,PasswordEncoder passwordEncoder) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) throws Exception {
        System.out.println("Logging in user: " + request.login());
        User user = userService.loginUser(request.login(), request.password());
        if (user != null) {
            String token = jwtUtils.generateToken(user.getLogin(),user.getRole());
            return ResponseEntity.ok(new AuthResponse(
                    token,
                    user.getId(),
                    user.getLogin(),
                    user.getEmail(),
                    user.getRole()

            ));
        }
        return ResponseEntity.status(401).body("Błędny login lub hasło");
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) throws Exception {
        System.out.println("Registering user: " + request.login());
        User user = new User();
        user.setLogin(request.login());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole("USER");
        System.out.println(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        userService.registerUser(user);

        String token = jwtUtils.generateToken(user.getLogin(),user.getRole());
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("token", token);
        responseBody.put("id", user.getId());
        responseBody.put("login", user.getLogin());
        responseBody.put("email", user.getEmail());


        return ResponseEntity.ok(responseBody);
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) throws Exception {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtUtils.getLoginFromToken(token);
            User user = userService.findByLogin(username);
            if (user != null) {
                return ResponseEntity.ok(new UserResponse(
                        user.getId(),
                        user.getLogin(),
                        user.getEmail()
                ));
            }
        }
        return ResponseEntity.status(401).build();
    }

    public record RegisterRequest(String login, String password,String email) {}
    public record LoginRequest(String login, String password) {}
    public record AuthResponse(String token, Long id, String login, String email,String role) {}
    public record UserResponse(Long id, String login, String email) {}
}
