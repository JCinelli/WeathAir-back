package com.weathair.entities.indicators;

import java.time.LocalDateTime;

import javax.persistence.Entity;

/**
 * @author tarbo Attribut des indicateurs météorologiques
 *
 */
@Entity
public class MeteoIndicator extends AbstractIndicator {

//	FIELDS
	private LocalDateTime dateTime;
	
	private String description;

	private Double temperature;
	
	private Double feelsLike;
	
	private Double windSpeed;
	
	private Integer windDeg;
	
	private Integer humidity;

//	CONSTRUCTOR
	public MeteoIndicator() {
		super();
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

//	GETTERS & SETTERS
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

	

}
