package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{

}
