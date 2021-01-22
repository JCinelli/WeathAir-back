package com.weathair.dto;

import com.weathair.entities.Role;
import com.weathair.entities.Township;
import com.weathair.entities.User;

public class UserDto {
	
	private String pseudo;
	private String email; 
	private String password;
	private Township township;
	private Role role;
	
	public UserDto() {
		super();
	}
	
	public UserDto(String pseudo, String email, String password, Township township, Role role) {
		super();
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.township = township;
		this.role = role;
	}

	public UserDto(User user) {
		this.pseudo = user.getPseudo();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Township getTownship() {
		return township;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
	
	

}
