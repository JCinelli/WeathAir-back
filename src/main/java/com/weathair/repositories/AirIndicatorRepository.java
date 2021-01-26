package com.weathair.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weathair.entities.indicators.AirIndicator;

public interface AirIndicatorRepository extends JpaRepository<AirIndicator, Integer> {

	@Query("FROM AirIndicator a JOIN a.township t WHERE t.name= :townshipName ORDER BY a.dateTime ASC LIMIT :limit")
	List<AirIndicator> findByTownshipName(String townshipName, int limit);
}
