package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.forum.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
