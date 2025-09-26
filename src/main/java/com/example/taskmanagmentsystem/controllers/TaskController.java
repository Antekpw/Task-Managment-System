package com.example.taskmanagmentsystem.controllers;

import com.example.taskmanagmentsystem.dto.TaskDto;
import com.example.taskmanagmentsystem.entities.Task;


import com.example.taskmanagmentsystem.services.TaskService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController  {

    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        List<TaskDto> taskDtos = tasks.stream()
                .map(TaskDto::from)
                .toList();

        return ResponseEntity.ok(taskDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        if (task != null) {
            return ResponseEntity.ok(TaskDto.from(task));
        }
        return ResponseEntity.notFound().build();
    }

    //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto dto) {
        System.out.println("Tworzenie zadania: " + dto.getName());
        System.out.println("Tworzenie zadania: " + dto.getDescription());
        System.out.println("id projekt: " + dto.getIdProject());
        System.out.println("Id user: " + dto.getIdUser());

        // ✅ Zapisz i zwróć
        Task createdTask = taskService.save(dto);
        TaskDto responseDto = TaskDto.from(createdTask);
        System.out.println("Id nowego zadania: " + responseDto.getId());
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.update(id, task);
        if (updatedTask != null) {
            return ResponseEntity.ok(TaskDto.from(updatedTask));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
