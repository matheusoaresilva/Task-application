package com.api.controller;

import com.api.controller.exception.CreateException;
import com.api.controller.exception.DeleteException;
import com.api.controller.exception.GetException;
import com.api.controller.exception.UpdateException;
import com.api.domain.models.TaskModel;
import com.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/tasks", produces = "application/json")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping
    public ResponseEntity<TaskModel> saveTask(@RequestBody TaskModel task){
        try {
            TaskModel savedTask = service.Create(task);
            return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
        }catch (Exception e) {
            throw new CreateException("Erro ao salvar tarefa: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable Long id){
        Optional<TaskModel> task = service.getTaskById(id);

        if(!task.isPresent()){
            throw new GetException("Tarefa com ID "+ id+ " não encontrada");
        } return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        try{
            service.deleteById(id);
        }catch (Exception e){
            throw new DeleteException("Erro ao excluir tarefa com ID "+ id +":" + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity <List<TaskModel>> findAll(){
        List<TaskModel> tasks= service.findAll();

        if (tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskModel> update(@PathVariable Long id, @RequestBody TaskModel model){
        Optional<TaskModel> currentTask = service.getTaskById(id);

        if (!currentTask.isPresent()){
            throw  new UpdateException("Tarefa com ID " + id + " não encontrada.");
        }try {
            model.setId(id);
            service.updateTask(model);
        }catch (Exception e){
                throw new UpdateException("Erro ao atualizar tarefa com ID "+ id + ": "+ e.getMessage());
        }

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
