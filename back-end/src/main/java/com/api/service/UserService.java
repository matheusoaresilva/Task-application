package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.models.UserModel;
import com.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository repository;

    public UserModel Create(UserModel model){
        return repository.save(model);
    }

    public Optional<UserModel> getUserById(Long id) {
        return repository.findById(id);
    }

    public UserModel updateUser(UserModel User){
        return repository.save(User);
    }

    public List<UserModel> findAll(){
        return repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
