package com.api.controller;

import com.api.domain.models.TaskModel;
import com.api.domain.models.enums.TaskCategoria;
import com.api.domain.models.enums.TaskStatus;
import com.api.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllTest {

    @Mock
    private TaskService service;

    @InjectMocks
    private TaskController controller;

//    @Test
//    void FindAll_ReturnTasks(){
//        List<TaskModel> expectedTasks = Arrays.asList(
//                new TaskModel(1L, "Task 1", "Description 1", TaskCategoria.PESSOAL, TaskStatus.EM_ANDAMENTO),
//                new TaskModel(2L, "Task 2", "Description 2", TaskCategoria.PESSOAL, TaskStatus.EM_ANDAMENTO),
//                new TaskModel(3L, "Task 3", "Description 3", TaskCategoria.PESSOAL, TaskStatus.EM_ANDAMENTO)
//        );
//
//        when(service.findAll()).thenReturn(expectedTasks);
//
//        ResponseEntity<List<TaskModel>> response = controller.findAll();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expectedTasks, response.getBody());
//    }

//    @Test
//    void FindAll_NoTasksFound(){
//        when(service.findAll()).thenReturn(Collections.emptyList());
//
//        ResponseEntity<List<TaskModel>> response = controller.findAll();
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        assertNull(response.getBody());
//    }
}
