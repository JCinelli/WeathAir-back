package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.Township;

public interface TownshipRepository extends JpaRepository<Township, String> {

}
