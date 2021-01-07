package com.weathair.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.weathair.entities.indicators.AbstractIndicator;

@Entity
public class Favorite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String duration;

	private String labelIndicator;

	@OneToMany(mappedBy = "favorite")
	private Set<AbstractIndicator> Indicator = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "id_town")
	private Township township;

	public Favorite() {
		super();
	}

	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
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

	@Override
	public String toString() {
		return "Favorite [id=" + id + ", duration=" + duration + ", labelIndicator=" + labelIndicator + ", Indicator="
				+ Indicator + ", township=" + township + "]";
	}
	

}
