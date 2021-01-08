package com.weathair.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.Township;

public interface TownshipRepository extends JpaRepository<Township, String> {
	
	List<Township> findByName(String name);

}
