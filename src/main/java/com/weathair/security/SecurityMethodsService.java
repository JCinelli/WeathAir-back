package com.weathair.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.weathair.entities.User;
import com.weathair.repositories.UserRepository;

@Service
public class SecurityMethodsService {

	@Autowired
	private UserRepository userRepository;
	
	public boolean isConnectedUser(Integer userId, UserDetails connectedUser) {
		User user = userRepository.findById(userId).orElse(null);
		return user != null && user.getEmail().equals(connectedUser.getUsername());
	}
}
