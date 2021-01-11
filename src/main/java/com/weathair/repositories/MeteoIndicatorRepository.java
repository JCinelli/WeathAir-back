package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.indicators.MeteoIndicator;

public interface MeteoIndicatorRepository  extends JpaRepository<MeteoIndicator, Integer>  {

	
}
