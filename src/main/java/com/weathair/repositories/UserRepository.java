package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
