package com.weathair.dto.forum;

import java.time.LocalDateTime;
import java.util.Date;

import com.weathair.entities.User;
import com.weathair.entities.forum.Post;

public class MessageDtoResponse {

	private Integer id;
	private String text;
	private Post post;
	private User user;
	private LocalDateTime date_time;
	
	public MessageDtoResponse() {
		super();
	}
	
	
	public LocalDateTime getDate_time() {
		return date_time;
	}


	public void setDate_time(LocalDateTime localDateTime) {
		this.date_time = localDateTime;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
