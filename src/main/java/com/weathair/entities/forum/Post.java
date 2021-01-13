package com.weathair.entities.forum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.weathair.entities.User;

/**
 * @author MIACHELL
 *
 * Class for Post
 * 
 * Primary Key id
 * 
 */
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title; 
	private String text; 
	private LocalDateTime dateTime;
	@OneToMany(mappedBy = "post")
	private List<Message> messages = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name="topic_id")
	private Topic topic;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	//CONSTRUCTOR
	public Post() {
		super();
		this.setDateTime();
	}

	//GETTERS & SETTERS
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

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime() {
		this.dateTime = LocalDateTime.now();
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
