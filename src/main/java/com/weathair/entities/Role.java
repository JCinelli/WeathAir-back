package com.weathair.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.weathair.enumerations.RoleEnumeration;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private RoleEnumeration label;
	
	@OneToMany(targetEntity = User.class, mappedBy = "role")
	private List<User> users = new ArrayList<>();
	
	
	public Role() {
		super();
	}
	
	public Role(RoleEnumeration label) {
		super();
		this.label = label;
	}

	public  RoleEnumeration getLabel() {
		return label;
	}

	public void setLabel(RoleEnumeration label) {
		this.label = label;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
