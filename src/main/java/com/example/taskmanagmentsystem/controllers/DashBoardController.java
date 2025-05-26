package com.example.taskmanagmentsystem.controllers;

import com.example.taskmanagmentsystem.dto.DashboardStatsDto;
import com.example.taskmanagmentsystem.dto.ProjectDto;
import com.example.taskmanagmentsystem.dto.TaskDto;
import com.example.taskmanagmentsystem.entities.User;
import com.example.taskmanagmentsystem.services.ProjectService;
import com.example.taskmanagmentsystem.services.TaskService;

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

    public DashBoardController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping("/my-tasks")
    public ResponseEntity<List<TaskDto>> getMyTasks(@AuthenticationPrincipal User user) {

        List<TaskDto> tasks = taskService.getTasksByAssignee(user.getId());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/my-projects")
    public ResponseEntity<List<ProjectDto>> getMyProjects(@AuthenticationPrincipal User user) {

        List<ProjectDto> projects = projectService.getProjectsByUserId(user.getId());
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDto> getDashboardStats(@AuthenticationPrincipal User user) {

        DashboardStatsDto stats = new DashboardStatsDto(
                taskService.countTasksByStatus(user, "TODO"),
                taskService.countTasksByStatus(user, "IN_PROGRESS"),
                taskService.countTasksByStatus(user, "DONE")
        );
        return ResponseEntity.ok(stats);
    }
}
