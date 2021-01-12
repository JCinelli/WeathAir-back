package com.weathair.dto;

import com.weathair.entities.Township;

public class UserDto {
	
	private String pseudo;
	private String email; 
	private String password;
	private Township township;
	private boolean isBan;
	
	public UserDto() {
		super();
	}
	
	public UserDto(String pseudo, String email, String password, Township township, boolean isBan) {
		super();
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.township = township;
		this.isBan = isBan;
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

	public boolean isBan() {
		return isBan;
	}

	public void setBan(boolean isBan) {
		this.isBan = isBan;
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
	

}
