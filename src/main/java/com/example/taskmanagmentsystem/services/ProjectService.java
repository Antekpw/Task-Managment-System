package com.example.taskmanagmentsystem.services;

import com.example.taskmanagmentsystem.entities.Project;
import com.example.taskmanagmentsystem.utils.GeneralRepository;
import com.example.taskmanagmentsystem.utils.GeneralService;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends GeneralService<Project> {

    public ProjectService(GeneralRepository<Project> repository) {
        super(repository);
    }


}
