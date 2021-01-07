package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.forum.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
