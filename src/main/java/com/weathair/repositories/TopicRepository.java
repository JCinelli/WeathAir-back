package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.forum.Topic;

/**
 * @author MIACHELL
 * 
 * Interface TopicRepository for CRUD into Topic table 
 *
 */
public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
