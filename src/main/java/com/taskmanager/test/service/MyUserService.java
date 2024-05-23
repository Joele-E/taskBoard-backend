package com.taskmanager.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskmanager.test.dto.MyUserDto;
import com.taskmanager.test.model.AuthenticationResponse;
import com.taskmanager.test.model.MyUser;
import com.taskmanager.test.respository.MyUserRepository;

@Service
public class MyUserService {

	@Autowired
	MyUserRepository userRepo;
	
	@Autowired
	JwtService jwtService;
	
	//@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public MyUserService(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public List<MyUserDto> getUsersDto() {
		List<MyUser> userList = userRepo.findAll();

		try {
			List<MyUserDto> newList = MyUserDto.convertUsers(userList);
			return newList;
		} catch (Exception e) {
			return null;
		}
	}

	public MyUser addUser(MyUser u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return userRepo.save(u);

	}
	
	public AuthenticationResponse register(MyUser u) {
		MyUser user = new MyUser();
		user.setUsername(u.getUsername());
		user.setPassword(passwordEncoder.encode(u.getPassword()));
		user.setCreated_at(u.getCreated_at());
		user.setTasks(u.getTasks());
		user.setRole("USER");
		
		user = userRepo.save(user);
		
		String token = jwtService.generateToken(user);
		
		return new AuthenticationResponse(token);
	}
	
	public AuthenticationResponse authenticate(MyUser u) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						u.getUsername(), 
						u.getPassword()
				)
			);
		MyUser user = userRepo.findByUsername(u.getUsername()).orElseThrow();
		
		String token = jwtService.generateToken(user);
		
		return new AuthenticationResponse(token);
	}

	public MyUserDto getUserDto(Long id) {
		
		try {
			return new MyUserDto(userRepo.findById(id).orElse(null));
		} catch (Exception e) {
			return null;
		}
	}

	public MyUserDto updateUser(Long id, MyUser user) {
		MyUser oldUser = userRepo.findById(id).orElse(null);
		
		if (oldUser != null) {
			oldUser.setUsername(user.getUsername() != null ? user.getUsername() : oldUser.getUsername());
			oldUser.setPassword(user.getPassword() != null ? user.getPassword() : oldUser.getPassword());
			oldUser.setTasks(user.getTasks() != null ? user.getTasks() : oldUser.getTasks());
			
			return new MyUserDto(oldUser);
		}else {
			return null;
		}
	}

	public boolean deleteUser(Long id) {
		try {
			userRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
