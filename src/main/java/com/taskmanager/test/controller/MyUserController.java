package com.taskmanager.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.test.dto.MyUserDto;
import com.taskmanager.test.model.AuthenticationResponse;
import com.taskmanager.test.model.MyUser;
import com.taskmanager.test.service.MyUserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class MyUserController {

	@Autowired
	MyUserService userService;

	@GetMapping("/users")
	public List<MyUserDto> getUsersDto() {
		return userService.getUsersDto();
	}
	
	@GetMapping("/users/{id}")
	public MyUserDto getUser(@PathVariable Long id) {
		return userService.getUserDto(id);
	}

	@PostMapping("/users")
	public MyUser addUser(@RequestBody MyUser u) {
		return userService.addUser(u);
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody MyUser user) {
		
		return ResponseEntity.ok(userService.register(user));
		
	}
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody MyUser user) {
		
		return ResponseEntity.ok(userService.authenticate(user));
		
	}
	
	
	
	@PatchMapping("/users/{id}")
	public MyUserDto updateUser(@PathVariable Long id, @RequestBody MyUser user) {
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/users/{id}")
	public boolean deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
}


