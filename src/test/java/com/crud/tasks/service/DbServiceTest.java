package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Test
    void getAllTasks() {

        //Given
        Task task = new Task(null, "test class task", "test task");
        Task taskOne = new Task(null, "test class task", "test task");

        dbService.saveTask(task);
        dbService.saveTask(taskOne);

        //When
        long id = task.getId();
        long idOne = taskOne.getId();
        List<Task> tasks  = dbService.getAllTasks();

        //Then
 //       assertEquals(12, tasks.size());

        //Cleanup
        dbService.deleteTask(id);
        dbService.deleteTask(idOne);

    }

    @Test
    void getTaskTest() throws Exception{

        //Given
        Task task = new Task(null, "test task", "test");

        //When
        dbService.saveTask(task);

        //Then
        long id = task.getId();
        List<Task> tasks  = dbService.getAllTasks();
        Task findTask = dbService.getTask(id);

        assertEquals(task.getTitle(), findTask.getTitle());

        //Cleanup
        dbService.deleteTask(id);

    }

    @Test
    void saveTask() {
    }

    @Test
    void deleteTask() {
    }
}