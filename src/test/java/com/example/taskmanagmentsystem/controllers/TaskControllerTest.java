package com.example.taskmanagmentsystem.controllers;

import com.example.taskmanagmentsystem.config.SecurityConfig;
import com.example.taskmanagmentsystem.entities.Task;
import com.example.taskmanagmentsystem.services.TaskService;
import com.example.taskmanagmentsystem.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
@AutoConfigureMockMvc(addFilters = false)
@Import(SecurityConfig.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;
    @MockBean private JwtUtils jwtUtils;

//    @Test
//    void shouldCreateTask() throws Exception {
//        // given
//        Task task = new Task();
//        task.setName("New Task");
//        when(taskService.save(any(Task.class))).thenReturn(task);
//        when(jwtUtils.getLoginFromToken(any())).thenReturn("testuser");
//        // when/then
//        mockMvc.perform(post("/api/tasks")
//                        .with(csrf())
//                        .header("Authorization", "Bearer valid-test-token")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//            {
//                "name": "New Task",
//                "description": "Some description",
//                "creationDate": "2024-01-01T00:00:00Z",
//                "status": "NEW",
//                "idUser": {"id": 1},
//                "idProject": {"id": 1}
//            }
//        """))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("New Task"));
//    }
}
