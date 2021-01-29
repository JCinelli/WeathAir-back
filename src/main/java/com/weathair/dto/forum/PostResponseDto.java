package com.weathair.dto.forum;

import com.weathair.entities.User;
import com.weathair.entities.forum.Topic;

public class PostResponseDto {
	private Integer id;
	private String title; 
	private String text; 
	private Topic topic;
	private User user;
	
	public PostResponseDto() {
		super();
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
