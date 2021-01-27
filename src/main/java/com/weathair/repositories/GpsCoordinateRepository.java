package com.weathair.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weathair.entities.GpsCoordinate;

public interface GpsCoordinateRepository extends JpaRepository<GpsCoordinate, Integer> {

	@Query("SELECT g FROM GpsCoordinate g JOIN g.township t WHERE t.name= :townshipName")
	List<GpsCoordinate> findByTownshipName(String townshipName);
	
}
