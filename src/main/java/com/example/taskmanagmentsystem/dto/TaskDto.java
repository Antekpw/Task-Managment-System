package com.example.taskmanagmentsystem.dto;

import com.example.taskmanagmentsystem.entities.Task;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class TaskDto {
    private Long id;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant creationDate;
    private String status;
    private Long idUser;
    private Long idProject;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String projectName;

    // --- Gettery i Settery ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public static TaskDto from(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setCreationDate(task.getCreationDate());
        System.out.println("TaskDto.from: " + task.getCreationDate());
        dto.setStatus(task.getStatus());
        dto.setIdUser(task.getIdUser() != null ? task.getIdUser().getId() : null);
        dto.setIdProject(task.getIdProject() != null ? task.getIdProject().getId() : null);
        dto.setProjectName(task.getIdProject() != null ? task.getIdProject().getName() : null);

        return dto;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
