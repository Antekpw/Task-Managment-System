package com.example.taskmanagmentsystem.Repositories;


import com.example.taskmanagmentsystem.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findBySharedUsersId(Long userId);
    // Custom query methods can be defined here if needed
    // For example:
    // List<Project> findByName(String name);
    // List<Project> findByStatus(String status);
    @Query("SELECT p FROM Project p JOIN p.sharedUsers u WHERE u.id = :userId")
    List<Project> findProjectsByUserId(Long userId);// Wyszukaj projekty, w których użytkownik jest współdzielony
}
