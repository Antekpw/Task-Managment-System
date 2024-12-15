package com.example.taskmanagmentsystem.controllers;


import com.example.taskmanagmentsystem.Repositories.UserRepository;
import com.example.taskmanagmentsystem.entities.Project;

import com.example.taskmanagmentsystem.entities.User;
import com.example.taskmanagmentsystem.services.ProjectService;
import com.example.taskmanagmentsystem.services.UserService;
import com.example.taskmanagmentsystem.utils.GeneralController;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/users")
public class UserController extends GeneralController<User> {

    private final UserService userService;
    private final ProjectService projectservice;

    public UserController(UserRepository userRepository, UserService userService, ProjectService projectService) {
        super(userRepository);
        this.userService = userService;
        this.projectservice = projectService;
    }

    @PostMapping("/{id}/projects/{projectId}")
    public ResponseEntity<User> assignUserToProject(@PathVariable Long id, @PathVariable Long projectId) {

        Project project = projectservice.findByID(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id: " + id + "not existing"));
        User user = userService.findByID(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + "not existing"));
        user.getProjects().add(project);
        project.setIdUser(user);
        userService.createEntity(user);
        return ResponseEntity.ok(user);
    }
}

