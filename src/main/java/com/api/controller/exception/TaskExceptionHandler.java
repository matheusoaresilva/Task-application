package com.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.api.controller.exception.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CreateException.class)
    public final ResponseEntity<ErrorResponse> handleTaskSaveException(CreateException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Erro ao salvar tarefa", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ReadException.class)
    public final ResponseEntity<ErrorResponse> handleReadTaskException(ReadException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Erro ao ler tarefa", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UpdateException.class)
    public final ResponseEntity<ErrorResponse> handleUpdateTaskException(UpdateException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Erro ao atualizar tarefa", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeleteException.class)
    public final ResponseEntity<ErrorResponse> handleDeleteTaskException(DeleteException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Erro ao deletar tarefa", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GetException.class)
    public final ResponseEntity<ErrorResponse> handleGetTaskException(GetException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Erro ao buscar tarefa", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
