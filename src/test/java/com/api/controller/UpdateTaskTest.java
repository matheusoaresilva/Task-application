package com.api.controller;

import com.api.controller.exception.UpdateException;
import com.api.domain.models.TaskModel;
import com.api.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateTaskTest {

    @Mock
    private TaskService service;


    @InjectMocks
    private TaskController controller;

    @Test
    void UpdateTask_Sucess(){
        Long id = 1L;
        TaskModel task = new TaskModel();
        task.setTitulo("Test title");
        task.setDescricao("Description test");
        task.setId(id);

        Optional<TaskModel> optional = Optional.of(task);

        when(service.getTaskById(id)).thenReturn(optional);

        ResponseEntity<TaskModel> response = controller.update(id, task);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());

        verify(service).updateTask(task);
    }

    @Test
    void UpdateTask_ThrowException(){
        Long id = 1L;
        TaskModel task = new TaskModel();
        task.setTitulo("Test title");
        task.setDescricao("Description test");
        task.setId(id);

        Optional<TaskModel> optional = Optional.empty();

        when(service.getTaskById(id)).thenReturn(optional);

        Exception exception = assertThrows(UpdateException.class, () -> controller.update(id, task));

        assertEquals("Tarefa com ID 1 não encontrada.", exception.getMessage());
    }
}
