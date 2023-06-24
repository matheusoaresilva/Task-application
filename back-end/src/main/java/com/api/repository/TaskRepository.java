package com.api.repository;

import com.api.domain.models.TaskModel;
import com.api.domain.models.UserModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {

	List<TaskModel> findByUser(UserModel currentUser);
}
