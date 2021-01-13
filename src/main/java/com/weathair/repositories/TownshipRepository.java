package com.weathair.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.Township;

public interface TownshipRepository extends JpaRepository<Township, String> {
	
	List<Township> findByName(String name);
	List<Township> findByNameOrderByPopulationDesc(String name);
	List<Township> findByNameContainingOrderByPopulationDesc(String name);

}
