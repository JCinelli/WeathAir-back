package com.weathair.dto.forum;

import java.time.LocalDateTime;

/**
 * @author MIACHELL
 *
 * DTO for Post Entity
 */
public class PostDto {
	
	
	private String title; 
	private String text; 
	private Integer topicId;
	private Integer userId;
	private LocalDateTime dateTime;

	
	public PostDto() {
		super();
	}

	public PostDto(String title, String text, Integer topicId, Integer userId, 	LocalDateTime dateTime) {
		super();
		this.title = title;
		this.text = text;
		this.topicId = topicId;
		this.userId = userId;
		this.dateTime = dateTime;
	}

	//GETTERS
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}
}
