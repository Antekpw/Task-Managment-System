package com.example.taskmanagmentsystem.services;

import com.example.taskmanagmentsystem.Repositories.TaskRepository;
import com.example.taskmanagmentsystem.entities.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

//    @Test
//    void shouldSaveTask() {
//        // given
//        Task task = new Task();
//        task.setName("Test Task");
//        when(taskRepository.save(any(Task.class))).thenReturn(task);
//
//        // when
//        Task result = taskService.save(task);
//
//        // then
//        assertThat(result).isNotNull();
//        assertThat(result.getName()).isEqualTo("Test Task");
//    }
    @Test
    void shouldReturnNullWhenTaskNotFound() {
        // given
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        Task result = taskService.findById(1L);

        // then
        assertThat(result).isNull();
    }
}
