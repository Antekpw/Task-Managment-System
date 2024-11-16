package com.example.taskmanagmentsystem.controllers;

import com.example.taskmanagmentsystem.Repositories.TaskRepository;
import com.example.taskmanagmentsystem.entities.Task;


import com.example.taskmanagmentsystem.utils.GeneralController;
import com.example.taskmanagmentsystem.utils.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController extends GeneralController<Task> {

    public TaskController(TaskRepository taskRepository) {
        super(taskRepository);
    }

}
