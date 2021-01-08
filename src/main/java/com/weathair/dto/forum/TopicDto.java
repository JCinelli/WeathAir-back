package com.weathair.dto.forum;

import java.util.ArrayList;
import java.util.List;

import com.weathair.entities.forum.Post;

/**
 * @author MIACHELL
 *
 * DTO for Topic Entity
 */
public class TopicDto {
	
	private String label; 
	private List<Post> posts = new ArrayList<>();
	
	public TopicDto(String label, List<Post> posts) {
		super();
		this.label = label;
		this.posts = posts;
	}

	public TopicDto(String label) {
		super();
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public List<Post> getPosts() {
		return posts;
	}
	
}
