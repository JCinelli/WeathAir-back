package com.weathair.dto.forum;

import com.weathair.entities.User;
import com.weathair.entities.forum.Post;

public class MessageDtoResponse {

	private Integer id;
	private String texte;
	private Post post;
	private User user;
	
	public MessageDtoResponse() {
		super();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
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
