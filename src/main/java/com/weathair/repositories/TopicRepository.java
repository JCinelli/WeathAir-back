package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.forum.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
