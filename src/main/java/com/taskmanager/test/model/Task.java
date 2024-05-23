package com.taskmanager.test.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private MyUser user;
	
	@CreationTimestamp
	private Date created_at;
	
	private String title;
	private String content;
	private boolean isCompleted;
	private boolean isInProgress;
	public Task() {
	}
	public Task(MyUser user, Date created_at, String title, String content, boolean isCompleted, boolean isInProgress) {
		super();
		this.user = user;
		this.created_at = created_at;
		this.title = title;
		this.content = content;
		this.isCompleted = isCompleted;
		this.isInProgress = isInProgress;
	}
	
	public MyUser getUser() {
		return user;
	}
	public void setUser(MyUser user) {
		this.user = user;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
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
	public Long getId() {
		return id;
	}
	
	

	
}
