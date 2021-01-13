package com.weathair.dto.forum;

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
	
	public PostDto() {
		super();
	}

	public PostDto(String title, String text, Integer topicId, Integer userId) {
		super();
		this.title = title;
		this.text = text;
		this.topicId = topicId;
		this.userId = userId;
	}

	//GETTERS
	public String getTitle() {
		return title;
	}
	
	public String getText() {
		return text;
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
	
}
