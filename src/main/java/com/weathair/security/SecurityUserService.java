package com.weathair.security;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.weathair.entities.Role;
import com.weathair.entities.User;
import com.weathair.repositories.UserRepository;

@Service
@Transactional
public class SecurityUserService implements UserDetailsService {

	private UserRepository userRepository;

	public SecurityUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

		Set<GrantedAuthority> authorities = findAuthorities(user);
	
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
	}
	
	private Set<GrantedAuthority> findAuthorities(User user) {

		Set<GrantedAuthority> authorities = new HashSet<>();

		Role role = user.getRole();
		
		if (role != null) {
//			if (role.getRights() != null) {
//				authorities = role.getRights().stream().map(right -> new SimpleGrantedAuthority(right.getLabel().name()))
//						.collect(Collectors.toSet());
//			}
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getLabel().name()));
		}
		return authorities;
	}

}
