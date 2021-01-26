package com.weathair.dto;

import com.weathair.entities.Role;
import com.weathair.entities.Township;

public class UserResponseDto {
	
	private String pseudo;
	private String email; 
	private String password;
	private Township township;
	private Role role;
	
	public UserResponseDto() {
		super();
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Township getTownship() {
		return township;
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
