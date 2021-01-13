package com.weathair.dto.indicators;

import java.time.LocalDateTime;


public class AirIndicatorDto {

	
	private LocalDateTime dateTime;
	private String nameTownship;
	private Integer aqi;
	private Double no2;
	private Double o3;
	private Double pm10;
	

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getNameTownship() {
		return nameTownship;
	}

	public void setNameTownship(String nameTownship) {
		this.nameTownship = nameTownship;
	}

	public Integer getAqi() {
		return aqi;
	}

	public void setAqi(Integer aqi) {
		this.aqi = aqi;
	}

	public Double getNo2() {
		return no2;
	}

	public void setNo2(Double no2) {
		this.no2 = no2;
	}

	public Double getO3() {
		return o3;
	}

	public void setO3(Double o3) {
		this.o3 = o3;
	}

	public Double getPm10() {
		return pm10;
	}

	public void setPm10(Double pm10) {
		this.pm10 = pm10;
	}
	
}
