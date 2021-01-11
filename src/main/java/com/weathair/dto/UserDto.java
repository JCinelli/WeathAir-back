package com.weathair.dto;

import com.weathair.entities.Township;

public class UserDto {
	
	private String pseudo;
	private String email; 
	private String password;
	private Township township;
	
	public UserDto() {
		super();
	}
	
	public UserDto(String pseudo, String email, String password, Township township) {
		super();
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.township = township;
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

}
