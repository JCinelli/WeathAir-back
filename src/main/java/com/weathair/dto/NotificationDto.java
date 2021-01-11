package com.weathair.dto;

import java.time.LocalDateTime;

public class NotificationDto {
	
	private String label;
	private LocalDateTime dateTime;


	public NotificationDto() {
		super();
	}
	
	public NotificationDto(String label, LocalDateTime dateTime) {
		super();
		this.label = label;
		this.dateTime = dateTime;
	}

	public String getLabel() {
		return label;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

}
