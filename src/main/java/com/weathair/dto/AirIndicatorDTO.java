package com.weathair.dto;

import java.time.LocalDateTime;


public class AirIndicatorDTO {

	
	private LocalDateTime dateTime;

	private String nameStation;

	private String codeStation;

	private String label;

	private Double value;

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getNameStation() {
		return nameStation;
	}

	public void setNameStation(String nameStation) {
		this.nameStation = nameStation;
	}

	public String getCodeStation() {
		return codeStation;
	}

	public void setCodeStation(String codeStation) {
		this.codeStation = codeStation;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	
}
