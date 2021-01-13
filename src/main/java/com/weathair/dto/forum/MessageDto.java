package com.weathair.dto.forum;

import java.time.LocalDateTime;

import com.weathair.entities.User;

/**
 * @author MIACHELL
 * 
 * DTO for Message entity
 *
 */
public class MessageDto {
	
	private String text; 
	private LocalDateTime dateTime;
	private User user;
	
	//Constructor
	public MessageDto(String text, LocalDateTime dateTime, User user) {
		super();
		this.text = text;
		this.dateTime = dateTime;
		this.user = user;
	}

	//GETTERS
	public String getText() {
		return text;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public User getUser() {
		return user;
	}

}
