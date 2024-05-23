package com.taskmanager.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taskmanager.test.model.MyUser;
import com.taskmanager.test.model.Task;

@Configuration
public class BeansConfiguration {

	@Bean
	public MyUser myUser() {
		return new MyUser();
	}
	
	@Bean
	public Task task() {
		return new Task();
	}

	
}
