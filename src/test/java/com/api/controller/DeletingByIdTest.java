package com.api.controller;

import com.api.controller.exception.DeleteException;
import com.api.domain.models.TaskModel;
import com.api.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeletingByIdTest {

    @Mock
    private TaskService service;

    @InjectMocks
    private TaskController controller;

    @Test
    void DeletingByIdTest_Sucess(){
        Long id = 1L;

        controller.deleteById(id);
        verify(service).deleteById(id);
    }

    @Test
    void DeletingById_ThrowException(){
        Long id = 1L;

        Exception expectedException = new DeleteException("Test exception");

        doThrow(expectedException).when(service).deleteById(id);
        Exception exception = assertThrows(DeleteException.class, ()-> controller.deleteById(id));

        assertEquals("Erro ao excluir tarefa com ID 1:Test exception", exception.getMessage());
    }
}
