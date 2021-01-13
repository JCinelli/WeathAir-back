package com.weathair.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.Role;
import com.weathair.enumerations.RoleEnumeration;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByLabel(RoleEnumeration role);

}