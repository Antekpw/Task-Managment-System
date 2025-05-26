package com.example.taskmanagmentsystem.repositories;

import com.example.taskmanagmentsystem.Repositories.TaskRepository;
import com.example.taskmanagmentsystem.entities.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    void shouldPersistTask() {
        // given
        Task task = new Task();
        task.setName("Test Task");
        task.setStatus("TODO");

        // when
        Task saved = taskRepository.save(task);

        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(taskRepository.findById(saved.getId()))
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("title", "Test Task");
    }
}
