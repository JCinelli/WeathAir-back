package com.weathair.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.entities.Role;
import com.weathair.enumerations.RoleEnumeration;
import com.weathair.exceptions.RepositoryException;
import com.weathair.repositories.RoleRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	public Integer getId(RoleEnumeration role) throws RepositoryException {
		Optional<Role> optRole = roleRepository.findByLabel(role);
		if (optRole.isPresent()) {
			return optRole.get().getId();
		} else
			throw new RepositoryException("No user id found for " + role.name());
	}
}
