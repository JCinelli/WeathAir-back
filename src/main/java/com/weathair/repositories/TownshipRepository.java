package com.weathair.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weathair.entities.Township;

public interface TownshipRepository extends JpaRepository<Township, String> {
	
	List<Township> findByName(String name);
	
	List<Township> findByNameOrderByPopulationDesc(String name);
	
	@Query("SELECT DISTINCT ai.township FROM AirIndicator ai")
	List<Township> findInAirIndicator();

	List<Township> findByNameContainingOrderByPopulationDesc(String name);
}
