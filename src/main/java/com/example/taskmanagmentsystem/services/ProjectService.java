package com.example.taskmanagmentsystem.services;

import com.example.taskmanagmentsystem.Repositories.ProjectRepository;
import com.example.taskmanagmentsystem.dto.ProjectDto;
import com.example.taskmanagmentsystem.entities.Project;
import com.example.taskmanagmentsystem.entities.Task;
import com.example.taskmanagmentsystem.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    public ProjectService(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    public List<ProjectDto> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ProjectDto findById(Long id) {
        return projectRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Transactional
    public ProjectDto save(ProjectDto projectDto) {
        Project project = toEntity(projectDto);
        project.setCreationDate(Instant.now());

        Project savedProject = projectRepository.save(project);
        return toDto(savedProject);
    }

    @Transactional
    public ProjectDto update(Long id, ProjectDto projectDto) {
        return projectRepository.findById(id)
                .map(existingProject -> {
                    updateEntityFromDto(projectDto, existingProject);
                    Project updatedProject = projectRepository.save(existingProject);
                    return toDto(updatedProject);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Ręczne mapowanie DTO <-> Entity
    public ProjectDto toDto(Project project) {
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setCreationDate(project.getCreationDate());

        // Mapowanie sharedUsers
        Set<Long> sharedUserIds = project.getSharedUsers().stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        dto.setSharedUserIds(sharedUserIds);

        // Mapowanie tasków
        Set<Long> taskIds = project.getTasks().stream()
                .map(Task::getId)
                .collect(Collectors.toSet());
        dto.setTaskIds(taskIds);

        return dto;
    }
    public Project toEntity(ProjectDto dto) {
        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setCreationDate(dto.getCreationDate());

        // Mapowanie sharedUsers
        if (dto.getSharedUserIds() != null) {
            Set<User> sharedUsers = dto.getSharedUserIds().stream()
                    .map(userService::findEntityById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            project.setSharedUsers(sharedUsers);
        }

        return project;
    }


    public void updateEntityFromDto(ProjectDto dto, Project project) {
        if (dto.getName() != null) {
            project.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            project.setDescription(dto.getDescription());
        }

        // Aktualizacja sharedUsers tylko jeśli podano w DTO
        if (dto.getSharedUserIds() != null) {
            Set<User> sharedUsers = dto.getSharedUserIds().stream()
                    .map(userService::findEntityById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            project.setSharedUsers(sharedUsers);
        }
    }

    // Metoda pomocnicza dla innych serwisów
    public Optional<Project> findEntityById(Long id) {
        return projectRepository.findById(id);
    }
    public List<ProjectDto> getProjectsByUserId(Long userId) {
        // Wersja 1: Jeśli projekty są przypisane przez sharedUsers
        List<Project> projects = projectRepository.findBySharedUsersId(userId);

        // Wersja 2: Jeśli potrzebujesz również projekty, gdzie użytkownik jest właścicielem
        // List<Project> projects = projectRepository.findByOwnerIdOrSharedUsersId(userId, userId);

        return projects.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}