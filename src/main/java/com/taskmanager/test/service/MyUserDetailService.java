package com.taskmanager.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskmanager.test.model.MyUser;
import com.taskmanager.test.respository.MyUserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private MyUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MyUser> user = userRepo.findByUsername(username);
		
		if (user.isPresent()) {
			MyUser userObj = user.get();
			return User.builder()
					.username(userObj.getUsername())
					.password(userObj.getPassword())
					.roles(getRoles(userObj))
					.build();
			
		} else {
			throw new UsernameNotFoundException("Bad credentials");
		}
	}

	private String[] getRoles(MyUser userObj) {
		if (userObj.getRole() == null) {
			return new String[]{"USER"} ;
		} else {
			return userObj.getRole().split(",");
		}
	}

}
