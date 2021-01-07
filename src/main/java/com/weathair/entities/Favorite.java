package com.weathair.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Favorite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String duration;

	private String labelIndicator;

	@OneToMany(mappedBy = "favorite")
	private Set<AbstractIndicator> Indicator = new HashSet<>();

	public Favorite() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getLabelIndicator() {
		return labelIndicator;
	}

	public void setLabelIndicator(String labelIndicator) {
		this.labelIndicator = labelIndicator;
	}

	public Set<AbstractIndicator> getIndicator() {
		return Indicator;
	}

	public void setIndicator(Set<AbstractIndicator> indicator) {
		Indicator = indicator;
	}



}
