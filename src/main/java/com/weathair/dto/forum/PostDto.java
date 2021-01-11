package com.weathair.dto.forum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.weathair.entities.User;
import com.weathair.entities.forum.Message;

/**
 * @author MIACHELL
 *
 * DTO for Post Entity
 */
public class PostDto {

	private String title; 
	private String text; 
	private LocalDateTime dateTime;
	private List<Message> messages = new ArrayList<>();
	private User user;
	
	public PostDto(String title, String text, LocalDateTime dateTime) {
		super();
		this.title = title;
		this.text = text;
		this.dateTime = dateTime;
	}

	public PostDto(String title, String text, LocalDateTime dateTime, List<Message> messages, User user) {
		super();
		this.title = title;
		this.text = text;
		this.dateTime = dateTime;
		this.messages = messages;
		this.user = user;
	}

	//GETTERS
	public String getTitle() {
		return title;
	}
	
	public String getText() {
		return text;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public User getUser() {
		return user;
	}
	
}
