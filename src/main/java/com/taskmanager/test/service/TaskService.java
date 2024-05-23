package com.taskmanager.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.test.dto.TaskDto;
import com.taskmanager.test.model.MyUser;
import com.taskmanager.test.model.Task;
import com.taskmanager.test.respository.MyUserRepository;
import com.taskmanager.test.respository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepo;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MyUserRepository userRepo;

	public List<TaskDto> getTasks(String authorization) {

		if (authorization.startsWith("Bearer ")) {
			String token = authorization.substring(7);
			String username = jwtService.extractUsername(token);
			MyUser user = userRepo.findByUsername(username).orElse(null);
			List<Task> tasks = taskRepo.findByUser(user).orElse(null);
			List<TaskDto> returnTasks = new ArrayList<TaskDto>();
			if (user != null && tasks != null) {
				try {
					returnTasks = TaskDto.convertTasks(tasks);
					return returnTasks;

				} catch (Exception e) {
					return null;
				}

			}

		}

		return null;

	}

	public TaskDto addTask(Task t, String authorization) {

		if (authorization.startsWith("Bearer ")) {
			String token = authorization.substring(7);
			String username = jwtService.extractUsername(token);
			MyUser user = userRepo.findByUsername(username).orElse(null);
			if (user != null) {

				t.setCompleted(false);
				t.setInProgress(false);
				t.setUser(user);

				try {
					taskRepo.save(t);

					return new TaskDto(t);
				} catch (Exception e) {
					return null;
				}

			}

		}
		return null;

	}

	public TaskDto getTask(Long id) {
		try {
			return new TaskDto(taskRepo.findById(id).orElse(null));

		} catch (Exception e) {
			return null;
		}
	}

	public TaskDto updateTask(Long id, Task task, String authorization, boolean isStatus) {

		if (authorization.startsWith("Bearer ")) {
			String token = authorization.substring(7);
			String username = jwtService.extractUsername(token);
			MyUser user = userRepo.findByUsername(username).orElse(null);
			Task oldTask = taskRepo.findById(id).orElse(null);
			if (user != null && oldTask != null && oldTask.getUser().getUsername().equals(username)) {

				oldTask.setUser(oldTask.getUser());
				oldTask.setTitle(task.getTitle() != null ? task.getTitle() : oldTask.getTitle());
				oldTask.setContent(task.getContent() != null ? task.getContent() : oldTask.getContent());
				oldTask.setCompleted(!isStatus ? oldTask.isCompleted() : task.isCompleted());
				oldTask.setInProgress(!isStatus ? oldTask.isInProgress() : task.isInProgress());
				oldTask.setCreated_at(oldTask.getCreated_at());
		
//					System.out.println(task.isCompleted());
//					System.out.println(oldTask.isCompleted());
				taskRepo.save(oldTask);

				return new TaskDto(oldTask);

			}

		}
		return null;

	}
	public TaskDto updateTaskStatus(Long id, Task task, String authorization) {

		if (authorization.startsWith("Bearer ")) {
			String token = authorization.substring(7);
			String username = jwtService.extractUsername(token);
			MyUser user = userRepo.findByUsername(username).orElse(null);
			Task oldTask = taskRepo.findById(id).orElse(null);
			if (user != null && oldTask != null && oldTask.getUser().getUsername().equals(username)) {

				oldTask.setUser(oldTask.getUser());
				oldTask.setTitle(task.getTitle() != null ? task.getTitle() : oldTask.getTitle());
				oldTask.setContent(task.getContent() != null ? task.getContent() : oldTask.getContent());
				oldTask.setCompleted(task.isCompleted());
				oldTask.setInProgress(task.isInProgress());
				oldTask.setCreated_at(oldTask.getCreated_at());
		
//					System.out.println(task.isCompleted());
//					System.out.println(oldTask.isCompleted());
				taskRepo.save(oldTask);

				return new TaskDto(oldTask);

			}

		}
		return null;

	}


	public boolean deleteTask(Long id, String authorization) {
		if (authorization.startsWith("Bearer ")) {
			String token = authorization.substring(7);
			String username = jwtService.extractUsername(token);
			MyUser user = userRepo.findByUsername(username).orElse(null);
			Task task = taskRepo.findById(id).orElse(null);
			if (user != null && task.getUser().getUsername().equals(username)) {
				try {
					taskRepo.deleteById(id);
					return true;
				} catch (Exception e) {
					return false;
				}

			}

		}
		return false;

	}

}
