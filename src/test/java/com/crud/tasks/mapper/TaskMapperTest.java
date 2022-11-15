package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTaskTest() {

        //Given
        Task task = new Task(null, "test class task", "test task");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(task.getTitle(), taskDto.getTitle());
    }

    @Test
    void mapToTaskDtoTest() {

        //Given
        TaskDto taskDto = new TaskDto(null, "test class task", "test task");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(taskDto.getContent(), task.getContent());

}

    @Test
    void mapToTaskDtoListTest() {

        Task task = new Task(null, "test class task", "test task");
        Task taskOne = new Task(null, "test class task", "test task");
        List<Task> tasks = List.of(task, taskOne);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(tasks.size(), taskDtoList.size());
        assertEquals(taskDtoList.get(0).getContent(), "test task");
    }
}