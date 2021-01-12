package com.weathair.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import com.weathair.enumerations.RoleEnumeration;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private  RoleEnumeration label;
	
	@ManyToMany
	@JoinTable(name = "role_has_right",
	joinColumns = @JoinColumn(name = "role_id"),
	inverseJoinColumns = @JoinColumn(name = "right_id"),
	uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "right_id" }))
	private List<Right> rights = new ArrayList<>();
	
	@OneToMany(targetEntity = User.class, mappedBy = "role")
	private List<User> users = new ArrayList<>();

	public  RoleEnumeration getLabel() {
		return label;
	}

	public void setLabel(RoleEnumeration label) {
		this.label = label;
	}

	public List<Right> getRights() {
		return rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
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
