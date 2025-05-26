package com.example.taskmanagmentsystem.Repositories;


import com.example.taskmanagmentsystem.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findBySharedUsersId(Long userId);
    // Custom query methods can be defined here if needed
    // For example:
    // List<Project> findByName(String name);
    // List<Project> findByStatus(String status);
}
