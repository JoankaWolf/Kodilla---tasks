package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskController taskController;

    @Test
    void shouldFetchEmptyTasksList() throws Exception{

        // Given
        when(taskController.getTasks()).thenReturn(ResponseEntity.ok(List.of()));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchTasksList() throws Exception {

        //Given
        List<TaskDto> taskDtoNewList = List.of(new TaskDto(1L, "Title1", "description"));
        when(taskController.getTasks()).thenReturn(ResponseEntity.ok(taskDtoNewList));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Title1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("description")));
    }

    @Test
    void shouldFetchTask() throws Exception {

        TaskDto taskDto = new TaskDto(1L, "Title1", "description");
        when(taskController.getTask(any(Long.class))).thenReturn(ResponseEntity.ok(taskDto));


        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Title1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("description")));
    }


    @Test
    void shouldDeleteTask() throws Exception {

        //Given, When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void shouldUpdateTask() throws Exception {

        //Given
        TaskDto taskDto = new TaskDto(1L, "Title1", "description");
        when(taskController.updateTask(any(TaskDto.class))).thenReturn(ResponseEntity.ok(taskDto));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Title1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("description")));
    }

    @Test
    void shouldCreateTask() throws Exception {

        //Given
        TaskDto taskDto = new TaskDto(1L, "Title1", "description");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}