package com.taskmanager.test.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanager.test.model.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long>{
	Optional<MyUser> findByUsername(String username);

}
