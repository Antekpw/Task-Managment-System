//package com.example.taskmanagmentsystem.services;
//
//import com.example.taskmanagmentsystem.Repositories.ProjectRepository;
//import com.example.taskmanagmentsystem.Repositories.UserRepository;
//import com.example.taskmanagmentsystem.dto.UserProjectAssignmentDto;
//import com.example.taskmanagmentsystem.entities.Project;
//import com.example.taskmanagmentsystem.entities.User;
//
//import com.example.taskmanagmentsystem.utils.GeneralService;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class ProjectAssignmentService {
//
//    private final UserRepository userRepository;
//    private final ProjectRepository projectRepository;
//    private final GeneralService<User> userService;
//    private final GeneralService<Project> projectService;
//
//    public ProjectAssignmentService(
//            UserRepository userRepository,
//            ProjectRepository projectRepository,
//            GeneralService<User> userService,
//            GeneralService<Project> projectService) {
//        this.userRepository = userRepository;
//        this.projectRepository = projectRepository;
//        this.userService = userService;
//        this.projectService = projectService;
//    }
//
//    @Transactional
//    public UserProjectAssignmentDto assignUserToProject(Long userId, Long projectId) {
//        User user = userService.findByID(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
//
//        Project project = projectService.findByID(projectId)
//                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));
//
//        // Sprawdź czy użytkownik już nie jest przypisany
//        if (user.getProjects().contains(project)) {
//            //throw new OperationNotAllowedException("User is already assigned to this project");
//        }
//
//        // Przypisz użytkownika do projektu
//        user.getProjects().add(project);
//       // project.setAssignedUser(user); // lub project.getAssignedUsers().add(user) w zależności od relacji
//
//        // Zapisz zmiany
//        User updatedUser = userService.createEntity(user);
//        projectService.createEntity(project);
//
//        return toDto(updatedUser, project);
//    }
//
//    @Transactional
//    public void removeUserFromProject(Long userId, Long projectId) {
//        User user = userService.findByID(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
//
//        Project project = projectService.findByID(projectId)
//                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));
//
//        if (!user.getProjects().contains(project)) {
//           // throw new OperationNotAllowedException("User is not assigned to this project");
//        }
//
//        user.getProjects().remove(project);
//        //project.setAssignedUser(null); // lub project.getAssignedUsers().remove(user)
//
//        userService.createEntity(user);
//        projectService.createEntity(project);
//    }
//
//    private UserProjectAssignmentDto toDto(User user, Project project) {
//        Set<Long> projectIds = user.getProjects().stream()
//                .map(Project::getId)
//                .collect(Collectors.toSet());
//
//        return new UserProjectAssignmentDto(
//                user.getId(),
//                user.getEmail(),
//                project.getId(),
//                project.getName(),
//                projectIds
//        );
//    }
//}