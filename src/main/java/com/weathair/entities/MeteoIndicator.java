package com.weathair.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author tarbo Attribut des indicateurs météorologiques
 *
 */
@Entity
public class MeteoIndicator extends AbstractIndicator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// date
	private LocalDateTime dateTime;
	// temperature
	private Integer temperature;
	// vent
	private Integer wind;
	// direction du vent
	private Integer dirWind;
	// probabilité de pluie entre 0 et 100%
	private Integer probaRain;
	// probabilité de brouillard entre 0 et 100%
	private Integer probaFog;

	public MeteoIndicator() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public Integer getWind() {
		return wind;
	}

	public void setWind(Integer wind) {
		this.wind = wind;
	}

	public Integer getDirWind() {
		return dirWind;
	}

	public void setDirWind(Integer dirWind) {
		this.dirWind = dirWind;
	}

	public Integer getProbaRain() {
		return probaRain;
	}

	public void setProbaRain(Integer probaRain) {
		this.probaRain = probaRain;
	}

	public Integer getProbaFog() {
		return probaFog;
	}

	public void setProbaFog(Integer probaFog) {
		this.probaFog = probaFog;
	}

}