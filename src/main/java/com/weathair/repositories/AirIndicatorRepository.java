package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.indicators.AirIndicator;

public interface AirIndicatorRepository extends JpaRepository<AirIndicator, Integer> {

}
