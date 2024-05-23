package com.taskmanager.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.test.dto.TaskDto;
import com.taskmanager.test.model.Task;
import com.taskmanager.test.service.TaskService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
	@Autowired
	TaskService taskService;
	
//	@GetMapping("/tasks")
//	public List<TaskDto> getTasks(){
//		
//		return taskService.getTasks();
//	}
	@GetMapping("/tasks")
	public List<TaskDto> getTasks(@RequestHeader (HttpHeaders.AUTHORIZATION) String authorization){
		
		return taskService.getTasks(authorization);
	}
	@GetMapping("/tasks/{id}")
	public TaskDto getTask(@PathVariable Long id) {
		return taskService.getTask(id);
	}
	
	@PostMapping("/tasks")
	public TaskDto addTask(@RequestBody Task t, @RequestHeader (HttpHeaders.AUTHORIZATION) String authorization ) {
		return taskService.addTask(t, authorization);
	}
	
	@PatchMapping("/tasks/{id}")
	public TaskDto updateTask(@PathVariable Long id, @RequestBody Task task,@RequestHeader (HttpHeaders.AUTHORIZATION) String authorization, @RequestParam(required = false) boolean status ) {
		
		return taskService.updateTask(id, task, authorization, status);
		
	}
	
	@DeleteMapping("/tasks/{id}")
	public boolean deleteTask(@PathVariable Long id, @RequestHeader (HttpHeaders.AUTHORIZATION) String authorization) {
		return taskService.deleteTask(id, authorization);
		
	}

}
