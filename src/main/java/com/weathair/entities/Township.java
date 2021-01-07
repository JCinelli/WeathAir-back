package com.weathair.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author jerem
 * 
 * Class for the towships
 * 
 * primary key => inseeCode
 *
 */
@Entity
public class Township {

//	FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String inseeCode;
	
	private String name;
	
	private int population;
	
	@OneToMany(mappedBy = "township")
	private List<User> users = new ArrayList<>();
	
	@OneToMany(mappedBy = "township")
	private List<AbstractIndicator> indicators = new ArrayList<>();

//	CONSTRUCTOR
	public Township() {
	}

//	METHODS
	@Override
	public String toString() {
		return "TownShip [inseeCode=" + inseeCode + ", name=" + name + ", population=" + population + "]";
	}

	//  GETTERS & SETTERS
	public String getInseeCode() {
		return inseeCode;
	}

	public void setInseeCode(String inseeCode) {
		this.inseeCode = inseeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
}
