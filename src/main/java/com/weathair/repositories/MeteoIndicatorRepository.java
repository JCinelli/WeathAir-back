package com.weathair.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weathair.entities.indicators.MeteoIndicator;

public interface MeteoIndicatorRepository  extends JpaRepository<MeteoIndicator, Integer>  {

	@Query("FROM MeteoIndicator m JOIN m.township t WHERE t.name= :townshipName ORDER BY m.dateTime ASC LIMIT :limit")
	List<MeteoIndicator> findByTownshipName(String townshipName, int limit);
	
}
