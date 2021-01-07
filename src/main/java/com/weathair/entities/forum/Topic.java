package com.weathair.entities.forum;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author MIACHELL
 *
 * Class for Topic
 * 
 * Primary Key id
 * 
 */
@Entity
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String label; 
	@OneToMany(mappedBy = "topic")
	private List<Post> posts = new ArrayList<>();
	
	//CONSTRUCTOR
	public Topic() {
		super();
	}

	//GETTERS & SETTERS
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
}
