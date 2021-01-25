package com.weathair.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.forum.Message;
import com.weathair.entities.forum.Post;

/**
 * @author MIACHELL
 * 
 * Interface MessageRepository for CRUD into Message table 
 *
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	List<Message> findByPost(Post post);

}
