package com.example.taskmanagmentsystem.dto;



import java.util.Set;

public record UserProjectAssignmentDto(
        Long userId,
        String userEmail,

        Long projectId,
        String projectName,
        Set<Long> assignedProjectIds
) {
    // Rekord automatycznie generuje konstruktor, gettery, equals, hashCode i toString
}
