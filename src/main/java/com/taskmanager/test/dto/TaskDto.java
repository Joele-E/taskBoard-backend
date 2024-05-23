package com.taskmanager.test.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taskmanager.test.model.Task;

public class TaskDto {
	private Long id;
	private String title;
	private String content;
	private Long userId;
	private Date createdAt;
	private boolean isCompleted;
	private boolean isInProgress;

	public TaskDto() {
		super();
	}

	public TaskDto(Long id, String title, String content, Long userId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.userId = userId;
	}

	public TaskDto(Task t) {
		this.id = t.getId();
		this.title = t.getTitle();
		this.content = t.getContent();
		this.userId = t.getUser().getId();
		this.isCompleted = t.isCompleted();
		this.isInProgress = t.isInProgress();
		this.createdAt = t.getCreated_at();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public boolean isInProgress() {
		return isInProgress;
	}

	public void setInProgress(boolean isInProgress) {
		this.isInProgress = isInProgress;
	}
	

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public static List<TaskDto> convertTasks(List<Task> tl) {
		List<TaskDto> newList = new ArrayList<TaskDto>();
		
		tl.forEach(t -> newList.add(new TaskDto(t)));
		
		return newList;
	}

}
