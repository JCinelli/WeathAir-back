package com.weathair.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.forum.Post;
import com.weathair.entities.forum.Topic;

/**
 * @author MIACHELL
 *
 * Interface PostRepository for CRUD into Post table 
 *
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByTopic(Topic topic);
}
