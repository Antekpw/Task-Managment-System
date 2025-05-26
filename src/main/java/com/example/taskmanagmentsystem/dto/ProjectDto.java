package com.example.taskmanagmentsystem.dto;

import com.example.taskmanagmentsystem.entities.Task;
import com.example.taskmanagmentsystem.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ProjectDto {
    private Long id;
    private String name;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant creationDate;

    private Set<Long> sharedUserIds = new HashSet<>();
    private Set<Long> taskIds = new HashSet<>();

    // Konstruktory
    public ProjectDto() {
    }

    public ProjectDto(Long id, String name, String description, Instant creationDate,
                      Set<Long> sharedUserIds, Set<Long> taskIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.sharedUserIds = sharedUserIds;
        this.taskIds = taskIds;
    }

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Long> getSharedUserIds() {
        return sharedUserIds;
    }

    public void setSharedUserIds(Set<Long> sharedUserIds) {
        this.sharedUserIds = sharedUserIds;
    }

    public Set<Long> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(Set<Long> taskIds) {
        this.taskIds = taskIds;
    }
}
