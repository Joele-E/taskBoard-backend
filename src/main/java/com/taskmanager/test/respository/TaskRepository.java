package com.taskmanager.test.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanager.test.model.MyUser;
import com.taskmanager.test.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	Optional<List<Task>> findByUser(MyUser user);
}
