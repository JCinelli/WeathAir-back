package com.weathair.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weathair.entities.indicators.AirIndicator;

public interface AirIndicatorRepository extends JpaRepository<AirIndicator, Integer> {

}
