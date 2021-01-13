package com.weathair.dto.forum;

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
	
	//Constructor
	public MessageDto(String text, Integer postId, Integer userId) {
		super();
		this.text = text;
		this.postId = postId;
		this.userId = userId;
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

}
