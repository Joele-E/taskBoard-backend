package com.taskmanager.test.dto;

import java.util.ArrayList;
import java.util.List;

import com.taskmanager.test.model.MyUser;

public class MyUserDto {

	private Long id;
	private String username;
	private List<TaskDto> tasks;

	public MyUserDto() {
	}

	public MyUserDto(Long id, String username, List<TaskDto> tasks) {
		super();
		this.id = id;
		this.username = username;
		this.tasks = tasks;
	}

	public MyUserDto(MyUser u) {
		this.id = u.getId();
		this.username = u.getUsername();
		this.tasks = TaskDto.convertTasks(u.getTasks());

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<TaskDto> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskDto> tasks) {
		this.tasks = tasks;
	}

	public static List<MyUserDto> convertUsers(List<MyUser> ul) {
		List<MyUserDto> newList = new ArrayList<MyUserDto>();
		ul.forEach(u -> newList.add(new MyUserDto(u)));

		return newList;
	}

}
