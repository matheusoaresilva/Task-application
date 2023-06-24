package com.api.service;

import com.api.domain.models.TaskModel;
import com.api.domain.models.UserModel;
import com.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public TaskModel Create(TaskModel model){
        return repository.save(model);
    }

    public Optional<TaskModel> getTaskById(Long id) {
        return repository.findById(id);
    }

    public TaskModel updateTask(TaskModel task){
        return repository.save(task);
    }

    public List<TaskModel> findAll(){
        return repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<TaskModel> findByUser(UserModel currentUser) {
        return repository.findByUser(currentUser);
    }

}
