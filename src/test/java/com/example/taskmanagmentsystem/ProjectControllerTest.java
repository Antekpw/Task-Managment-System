package com.example.taskmanagmentsystem;

import com.example.taskmanagmentsystem.controllers.AuthController;
import com.example.taskmanagmentsystem.controllers.ProjectController;
import com.example.taskmanagmentsystem.dto.ProjectDto;
import com.example.taskmanagmentsystem.services.ProjectService;
import com.example.taskmanagmentsystem.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProjectController.class,excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;


    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testCreating() throws Exception {

        ProjectDto dto = new ProjectDto();
        dto.setId(3L);
        dto.setName("projekt");
        dto.setDescription("description");
        dto.setCreationDate(Instant.now());
        dto.setSharedUserIds(new HashSet<>());
        dto.setTaskIds(new HashSet<>());
        when(projectService.save(any(ProjectDto.class))).thenReturn(dto);
        String json = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    @Test
    public void testGettingAllProjects() throws Exception{
        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk());
    }

}
