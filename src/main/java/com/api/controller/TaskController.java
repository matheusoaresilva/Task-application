package com.api.controller;

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
        TaskModel savedTask = service.Create(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable Long id){
        Optional<TaskModel> task = service.getTaskById(id);

        return task.map(model -> new ResponseEntity<>(model, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
         service.deleteById(id);
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        model.setId(id);
        service.updateTask(model);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
