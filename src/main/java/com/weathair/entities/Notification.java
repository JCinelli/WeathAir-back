package com.weathair.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * @author MIACHELL
 * 
 * Class for Notification
 * 
 * Primary Key id
 *
 */
@Entity
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String label;
	@ManyToMany
	@JoinTable(name = "user_notification", 
			joinColumns = @JoinColumn(name = "notification_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private List<User> users = new ArrayList<>();


	//CONSTRUCTOR
	public Notification() {
		super();
	}
	
	//GETTERS & SETTERS 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}