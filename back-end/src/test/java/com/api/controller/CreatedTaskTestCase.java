package com.api.controller;

import com.api.controller.exception.CreateException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatedTaskTestCase {

    @Mock
    private TaskService service;

    @InjectMocks
    private TaskController controller;

    @Test
    void testSaveTask_Success() {
        TaskModel task = new TaskModel();
        task.setTitulo("Tarefa 1");
        task.setDescricao("Descrição da tarefa 1");
        task.setCategoria(TaskCategoria.PESSOAL);
        task.setStatus(TaskStatus.EM_ANDAMENTO);

        TaskModel savedTask = new TaskModel();
        savedTask.setId(1L);
        savedTask.setTitulo("Tarefa 1");
        savedTask.setDescricao("Descrição da tarefa 1");
        savedTask.setCategoria(TaskCategoria.PESSOAL);
        savedTask.setStatus(TaskStatus.EM_ANDAMENTO);

        when(service.Create(task)).thenReturn(savedTask);

        ResponseEntity<TaskModel> response = controller.saveTask(task);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedTask, response.getBody());
        verify(service, times(1)).Create(task);
    }

    @Test
    void testSaveTask_InvalidTitle(){
        // Criando tarefa com titulo inválido
        TaskModel task = new TaskModel();
        task.setTitulo("");
        task.setDescricao("test descricao");
        task.setCategoria(TaskCategoria.PESSOAL);
        task.setStatus(TaskStatus.EM_ANDAMENTO);

        //Testando se foi lançada a exceção esperada
        assertThrows(CreateException.class, ()-> controller.saveTask(task));

        verify(service, times(0)).Create(task);
    }

    @Test
    void testSaveTask_InvalidDescription(){

        TaskModel task = new TaskModel();
        task.setTitulo("test title");
        task.setDescricao("");
        task.setCategoria(TaskCategoria.PESSOAL);
        task.setStatus(TaskStatus.EM_ANDAMENTO);

        assertThrows(CreateException.class, ()-> controller.saveTask(task));

        verify(service, times(0)).Create(task);
    }

    @Test
    void testSaveTask_InvalidCategory(){

        TaskModel task = new TaskModel();
        task.setTitulo("test title");
        task.setDescricao("test description");
        task.setCategoria(null);
        task.setStatus(TaskStatus.EM_ANDAMENTO);

        assertThrows(CreateException.class, ()-> controller.saveTask(task));

        verify(service, times(0)).Create(task);
    }

    @Test
    void testSaveTask_InvalidStatus(){

        TaskModel task = new TaskModel();
        task.setTitulo("test title");
        task.setDescricao("test description");
        task.setCategoria(TaskCategoria.PESSOAL);
        task.setStatus(null);

        assertThrows(CreateException.class, ()-> controller.saveTask(task));

        verify(service, times(0)).Create(task);
    }

}