package com.weathair.dto;

import com.weathair.entities.Township;

public class FavoriteDto {
	
	private String duration;
	private String labelIndicator;
	private Township township;
	
	public FavoriteDto() {
		super();
	}
	
	public FavoriteDto(String duration, String labelIndicator, Township township) {
		super();
		this.duration = duration;
		this.labelIndicator = labelIndicator;
		this.township = township;
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

}
