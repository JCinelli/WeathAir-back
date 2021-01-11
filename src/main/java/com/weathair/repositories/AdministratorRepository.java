package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

}
