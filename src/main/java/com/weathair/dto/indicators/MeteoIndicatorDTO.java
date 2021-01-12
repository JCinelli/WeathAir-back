
package com.weathair.dto.indicators;

import java.time.LocalDateTime;

import com.weathair.entities.Township;

public class MeteoIndicatorDTO {

//	FIELDS
	private LocalDateTime dateTime;
	
	private String description;

	private Double temperature;
	
	private Double feelsLike;
	
	private Double windSpeed;
	
	private Integer windDeg;
	
	private Integer humidity;
	
	private String townshipName;

//	CONSTUCTOR
	public MeteoIndicatorDTO() {
	}

//	GETTERS & SETTERS
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getFeelsLike() {
		return feelsLike;
	}

	public void setFeelsLike(Double feelsLike) {
		this.feelsLike = feelsLike;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Integer getWindDeg() {
		return windDeg;
	}

	public void setWindDeg(Integer windDeg) {
		this.windDeg = windDeg;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public String getTownshipName() {
		return townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	@Override
	public String toString() {
		return "MeteoIndicatorDTO [dateTime=" + dateTime + ", description=" + description + ", temperature="
				+ temperature + ", feelsLike=" + feelsLike + ", windSpeed=" + windSpeed + ", windDeg=" + windDeg
				+ ", humidity=" + humidity + ", townshipName=" + townshipName + "]";
	}
}
