package com.weathair.dto;

import com.weathair.entities.Township;
import com.weathair.entities.User;

public class FavoriteDto {
	
	private String duration;
	private String labelIndicator;
	private Township township;
	private User user;
	
	public FavoriteDto() {
		super();
	}
	
	public FavoriteDto(String duration, String labelIndicator, Township township, User user) {
		super();
		this.duration = duration;
		this.labelIndicator = labelIndicator;
		this.township = township;
		this.user = user;
	}

	public String getDuration() {
		return duration;
	}

	public String getLabelIndicator() {
		return labelIndicator;
	}

	public Township getTownship() {
		return township;
	}

	public User getUser() {
		return user;
	}

}
