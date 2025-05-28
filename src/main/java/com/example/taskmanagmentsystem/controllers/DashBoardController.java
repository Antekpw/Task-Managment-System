package com.example.taskmanagmentsystem.controllers;

import com.example.taskmanagmentsystem.Repositories.UserRepository;
import com.example.taskmanagmentsystem.dto.DashboardStatsDto;
import com.example.taskmanagmentsystem.dto.ProjectDto;
import com.example.taskmanagmentsystem.dto.TaskDto;
import com.example.taskmanagmentsystem.entities.User;
import com.example.taskmanagmentsystem.services.ProjectService;
import com.example.taskmanagmentsystem.services.TaskService;

import com.example.taskmanagmentsystem.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/dashboard")
//@PreAuthorize("hasRole('USER')")
public class DashBoardController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;
    public DashBoardController(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/my-tasks")
    public ResponseEntity<List<TaskDto>> getMyTasks(Authentication authentication) {
        String login = authentication.getName();
        System.out.println("Current user: " + login);

        User user = userService.findByLogin(login);

        List<TaskDto> tasks = taskService.getTasksByAssignee(user.getId());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/my-projects")
    public ResponseEntity<List<ProjectDto>> getMyProjects(Authentication authentication) {
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        List<ProjectDto> projects = userService.getProjectsByUserId(user.getId());
        System.out.println(projects.toString());
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDto> getDashboardStats(Authentication authentication) {
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        DashboardStatsDto stats = new DashboardStatsDto(
                taskService.countTasksByStatus(user, "TODO"),
                taskService.countTasksByStatus(user, "IN_PROGRESS"),
                taskService.countTasksByStatus(user, "DONE")
        );
        return ResponseEntity.ok(stats);
    }
}
