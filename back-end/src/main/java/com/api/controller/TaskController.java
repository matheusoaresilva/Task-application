package com.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.controller.exception.CreateException;
import com.api.controller.exception.DeleteException;
import com.api.controller.exception.GetException;
import com.api.controller.exception.UpdateException;
import com.api.domain.models.TaskModel;
import com.api.domain.models.UserModel;
import com.api.service.TaskService;


@RestController
@RequestMapping(value = "/tasks", produces = "application/json")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService service;

//    @PostMapping("/save")
//    public ResponseEntity<TaskModel> saveTask(@RequestBody TaskModel task){
//        if (task.getTitulo() == null || task.getTitulo().isEmpty()) {
//            throw new CreateException("Titulo da tarefa não pode ser vazio");
//        }
//        if (task.getDescricao()== null || task.getDescricao().isEmpty()){
//            throw new CreateException("A descrição não pode ser vazia");
//        }
//        if (task.getCategoria() == null){
//            throw new CreateException("A categoria não pode ser vazia");
//        }
//        if (task.getStatus() ==null){
//            throw new CreateException("O status não pode ser vazio");
//        }
//        try {
//            TaskModel savedTask = service.Create(task);
//            return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
//        }catch (CreateException e) {
//            throw e;
//        }
//    }
    @PostMapping("/save")
    public ResponseEntity<TaskModel> saveTask(Authentication authentication, @RequestBody TaskModel task){
    	UserModel currentUser = (UserModel) authentication.getPrincipal();
    	task.setUser(currentUser);
    	
    	// Resto do código para validação e salvamento da tarefa
        if (task.getTitulo() == null || task.getTitulo().isEmpty()) {
            throw new CreateException("Titulo da tarefa não pode ser vazio");
        }
        if (task.getDescricao() == null || task.getDescricao().isEmpty()) {
            throw new CreateException("A descrição não pode ser vazia");
        }
        if (task.getCategoria() == null) {
            throw new CreateException("A categoria não pode ser vazia");
        }
        if (task.getStatus() == null) {
            throw new CreateException("O status não pode ser vazio");
        }
        try {
        	TaskModel savedTask = service.Create(task);
        	return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
        	
        }catch(CreateException e) {
        	throw e;
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

    @GetMapping("/all")
    public ResponseEntity<List<TaskModel>> findAll(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        UserModel currentUser = (UserModel) authentication.getPrincipal();
        List<TaskModel> tasks = service.findByUser(currentUser);

        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
    }


    
//    @GetMapping("/all")
//    public ResponseEntity <List<TaskModel>> findAll(){
//        List<TaskModel> tasks= service.findAll();
//
//        if (tasks.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        else {
//            return new ResponseEntity<>(tasks, HttpStatus.OK);
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskModel> update(@PathVariable Long id, @RequestBody TaskModel model){
        Optional<TaskModel> currentTask = service.getTaskById(id);

        if (!currentTask.isPresent()){
            throw  new UpdateException("Tarefa com ID " + id + " não encontrada.");
        }try {
            model.setId(id);
            service.updateTask(model);
            System.out.println("Atualizada SETADO id: " + id);
        }catch (Exception e){
                throw new UpdateException("Erro ao atualizar tarefa com ID "+ id + ": "+ e.getMessage());
        }

        System.out.println("Atualizada id: " + id);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
