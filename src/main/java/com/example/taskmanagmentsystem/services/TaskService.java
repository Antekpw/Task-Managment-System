package com.example.taskmanagmentsystem.services;

import com.example.taskmanagmentsystem.Repositories.ProjectRepository;
import com.example.taskmanagmentsystem.Repositories.TaskRepository;
import com.example.taskmanagmentsystem.Repositories.UserRepository;
import com.example.taskmanagmentsystem.dto.TaskDto;
import com.example.taskmanagmentsystem.entities.Project;
import com.example.taskmanagmentsystem.entities.Task;
import com.example.taskmanagmentsystem.entities.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task save(TaskDto dto) {
        Project project = projectRepository.findById(dto.getIdProject())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Task task = new Task();
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setCreationDate(Instant.now());
        task.setIdProject(project);
        task.setIdUser(user);

        return taskRepository.save(task);
    }

    public Task update(Long id, Task task) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task updatedTask = existingTask.get();
            updateTaskFields(updatedTask, task);
            return taskRepository.save(updatedTask);
        }
        return null;
    }

    private void updateTaskFields(Task existingTask, Task newTask) {
        existingTask.setName(newTask.getName());
        existingTask.setDescription(newTask.getDescription());
        existingTask.setStatus(newTask.getStatus());
        existingTask.setCreationDate(newTask.getCreationDate());
        existingTask.setIdUser(newTask.getIdUser());
        existingTask.setIdProject(newTask.getIdProject());

    }

    public boolean delete(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<TaskDto> getTasksByAssignee(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        List<Task> tasks = taskRepository.findByIdUser(user);

        return tasks.stream()
                .map(TaskDto::from)
                .toList();
    }
    public long countTasksByStatus(User user, String status) {
        return taskRepository.countByIdUserAndStatus(user, status);
    }


}
