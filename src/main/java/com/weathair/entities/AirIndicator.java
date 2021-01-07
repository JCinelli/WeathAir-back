package com.weathair.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AirIndicator extends AbstractIndicator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	private LocalDateTime dateTime;

	private String nameStation;

	private String codeStation;

	private String label;

	private Double value;

	public AirIndicator() {
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

	@Override
	public String toString() {
		return "AirIndicator [id=" + id + ", dateTime=" + dateTime + ", nameStation=" + nameStation + ", codeStation="
				+ codeStation + ", label=" + label + ", value=" + value + "]";
	}

}
