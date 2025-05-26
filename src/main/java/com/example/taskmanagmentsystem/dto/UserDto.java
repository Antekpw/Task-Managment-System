package com.example.taskmanagmentsystem.dto;



import com.example.taskmanagmentsystem.entities.User;
import com.example.taskmanagmentsystem.entities.Project;

import com.example.taskmanagmentsystem.entities.Task;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDto {
    private Long id;
    private String login;
    private String email;
    private String role;
    private Set<Long> projectIds;
    private Set<Long> taskIds;

    // Konstruktory
    public UserDto() {
    }

    public UserDto(Long id, String login, String email, String role,
                   Set<Long> projectIds, Set<Long> taskIds) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.role = role;
        this.projectIds = projectIds;
        this.taskIds = taskIds;
    }

    // Metoda statyczna do konwersji z encji na DTO
    public static UserDto from(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        // Mapowanie projektów
        if (user.getProjects() != null) {
            Set<Long> projectIds = user.getProjects().stream()
                    .map(Project::getId)
                    .collect(Collectors.toSet());
            dto.setProjectIds(projectIds);
        }

        // Mapowanie zadań
        if (user.getTasks() != null) {
            Set<Long> taskIds = user.getTasks().stream()
                    .map(Task::getId)
                    .collect(Collectors.toSet());
            dto.setTaskIds(taskIds);
        }

        return dto;
    }

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Long> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(Set<Long> projectIds) {
        this.projectIds = projectIds;
    }

    public Set<Long> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(Set<Long> taskIds) {
        this.taskIds = taskIds;
    }
}
