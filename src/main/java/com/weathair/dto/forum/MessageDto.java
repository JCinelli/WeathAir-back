package com.weathair.dto.forum;

import java.time.LocalDateTime;

import com.weathair.entities.User;
import com.weathair.entities.forum.Post;

/**
 * @author MIACHELL
 * 
 * DTO for Message entity
 *
 */
public class MessageDto {
	
	private String text; 
	private LocalDateTime dateTime;
	private Post post;
	private User user;
	
	//Constructor
	public MessageDto(String text, LocalDateTime dateTime, Post post, User user) {
		super();
		this.text = text;
		this.dateTime = dateTime;
		this.post = post;
		this.user = user;
	}

	//GETTERS
	public String getText() {
		return text;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public Post getPost() {
		return post;
	}

	public User getUser() {
		return user;
	}

}
