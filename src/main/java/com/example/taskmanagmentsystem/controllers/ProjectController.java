package com.example.taskmanagmentsystem.controllers;

import com.example.taskmanagmentsystem.Repositories.ProjectRepository;
import com.example.taskmanagmentsystem.entities.Project;
import com.example.taskmanagmentsystem.utils.GeneralController;
import com.example.taskmanagmentsystem.utils.GeneralRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/projects")
public class ProjectController extends GeneralController<Project> {

    public ProjectController(ProjectRepository repository) {
        super(repository);
    }
}
