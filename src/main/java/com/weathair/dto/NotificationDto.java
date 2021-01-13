package com.weathair.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.weathair.entities.User;

public class NotificationDto {
	
	private String label;
	private LocalDateTime dateTime;
	private List<User> users;

	public NotificationDto() {
		super();
	}
	
	public NotificationDto(String label, LocalDateTime dateTime, List<User> users) {
		super();
		this.label = label;
		this.dateTime = dateTime;
		this.users = users;
	}

	public String getLabel() {
		return label;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public List<User> getUsers() {
		return users;
	}

}
