
package com.weathair.dto.indicators;

import java.time.LocalDateTime;

public class MeteoIndicatorDTO {

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
