package com.weathair.dto.forum;

import java.time.LocalDateTime;

/**
 * @author MIACHELL
 *
 * DTO for Topic Entity
 */
public class TopicDto {
	
	private String label; 
	private LocalDateTime dateTime;
	
	public TopicDto(String label, LocalDateTime dateTime) {
		super();
		this.label = label;
		this.dateTime = dateTime;
	}

	public String getLabel() {
		return label;
	}
	
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}
	
}
