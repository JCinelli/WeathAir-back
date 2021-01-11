package com.weathair.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.weathair.enumerations.RightEnumeration;

@Entity
@Table(name="right_")
public class Right {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private RightEnumeration label;
	
	@ManyToMany
	@JoinTable(
			name = "role_has_right", 
			joinColumns = @JoinColumn(name="right_id"),
			inverseJoinColumns = @JoinColumn(name="role_id"), 
			uniqueConstraints = @UniqueConstraint(columnNames = {"role_id","right_id"}))
	private List<Role>roles;

	public RightEnumeration getLabel() {
		return label;
	}

	public void setLabel(RightEnumeration label) {
		this.label = label;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
