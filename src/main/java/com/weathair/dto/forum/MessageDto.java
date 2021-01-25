package com.weathair.dto.forum;

import java.time.LocalDateTime;

/**
 * @author MIACHELL
 * 
 * DTO for Message entity
 *
 */
public class MessageDto {
	
	private String text; 
	private Integer postId;
	private Integer userId;
	private LocalDateTime dateTime;

	
	//Constructor
	public MessageDto(String text, Integer postId, Integer userId, LocalDateTime dateTime) {
		super();
		this.text = text;
		this.postId = postId;
		this.userId = userId;
		this.dateTime = dateTime;
	}

	//GETTERS
	public String getText() {
		return text;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPost(Integer postId) {
		this.postId = postId;
	}
	
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}

}
