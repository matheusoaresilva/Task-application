package com.api.controller;

import com.api.controller.exception.GetException;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetTaskByIdTest {

    @Mock
    private TaskService service;

    @InjectMocks
    private TaskController controller;

    @Test
    void testGetTaskById_Sucess(){
        Long id = 1L;
        TaskModel task =new TaskModel();
        task.setId(id);
        task.setTitulo("test title");
        task.setDescricao("test description");
        task.setCategoria(TaskCategoria.PESSOAL);
        task.setStatus(TaskStatus.EM_ANDAMENTO);

        // Transforma o objeto TaskModel em um objeto Optional
        Optional<TaskModel> optionalTask = Optional.of(task);
        // Configura o comportamento do mock de TaskService
        // para retornar o objeto Optional quando o método getTaskById for chamado
        when(service.getTaskById(id)).thenReturn(optionalTask);

        ResponseEntity<TaskModel> response = controller.getTaskById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
        verify(service, times(1)).getTaskById(id);
    }

    @Test
    void testGetTaskById_TaskNotFound(){
        Long id = 2L;
        when(service.getTaskById(id)).thenReturn(Optional.empty());

        assertThrows(GetException.class, ()-> controller.getTaskById(id));
        verify(service, times(1)).getTaskById(id);
    }
}
