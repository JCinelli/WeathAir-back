package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.forum.Post;

/**
 * @author MIACHELL
 *
 * Interface PostRepository for CRUD into Post table 
 *
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

}
