package com.weathair.dto.forum;

/**
 * @author MIACHELL
 *
 * DTO for Topic Entity
 */
public class TopicDto {
	
	private String label; 
	
	public TopicDto(String label) {
		super();
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
}
