package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.forum.Message;

/**
 * @author MIACHELL
 * 
 * Interface MessageRepository for CRUD into Message table 
 *
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
