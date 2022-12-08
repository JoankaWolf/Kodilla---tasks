package com.crud.tasks.domain;

import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TaskTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testTaskSave() {

        //Given
        Task task = new Task(null, "test class task", "test task");

        //When
        taskRepository.save(task);

        //Then
        long id = task.getId();
        List<Task> tasks  = taskRepository.findAll();

  //      assertEquals(12, tasks.size());

        //Cleanup
        taskRepository.deleteById(id);
    }

}