package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weathair.entities.indicators.MeteoIndicator;

public interface MeteoIndicatorRepository  extends JpaRepository<MeteoIndicator, Integer>  {

	
}
