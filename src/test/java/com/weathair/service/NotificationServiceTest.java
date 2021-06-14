package com.weathair.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.weathair.dto.NotificationDto;
import com.weathair.entities.Notification;
import com.weathair.entities.User;
import com.weathair.exceptions.NotificationException;
import com.weathair.exceptions.UserException;
import com.weathair.services.NotificationService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class NotificationServiceTest {

	@Autowired
	private NotificationService notificationService;
	
	@Test
	@Order(1)
	public void testFindAllNotifications() throws NotificationException {
		List<Notification> notifications = notificationService.findAllNotifications();
		assertThat(!notifications.isEmpty());
	}
	
	@Test
	@Order(2)
	public void findNotificationById() throws NotificationException {
		Notification notification = notificationService.findNotificationById(1);
		assertThat(notification.getLabel()).isEqualTo("bliblou");
	}
	
	@Test 
	@Order(3)
	public void testCreateNotification() throws NotificationException {
		NotificationDto notificationDto = new NotificationDto("notifs1", LocalDateTime.now(), new ArrayList<User>());
		
		this.notificationService.createNotification(notificationDto);
		
		List<Notification> notifications = this.notificationService.findAllNotifications();
		
		assertThat(notifications.get(notifications.size() - 1).getLabel()).isEqualTo("notifs1");
	}
	
	@Test
	@Order(4)
	public void testUpdateNotification() throws NotificationException {
		NotificationDto notificationDtoForUpdate = new NotificationDto("notifs1Updated", LocalDateTime.now(), new ArrayList<User>());
		
		List<Notification> notifications = this.notificationService.findAllNotifications();
		
		this.notificationService.updateNotification(notifications.get(notifications.size() - 1).getId(), notificationDtoForUpdate);
		
		notifications = this.notificationService.findAllNotifications();
		
		assertThat(notifications.get(notifications.size() - 1).getLabel()).isEqualTo("notifs1Updated");
	}
	
	@Test
	@Order(5)
	public void testDeleteNotification() throws NotificationException {
		
		List<Notification> notifications = this.notificationService.findAllNotifications();
		int notificationsSizeBefore = notifications.size();
		int lastIndex = notifications.get(notifications.size() - 1).getId();
		
		this.notificationService.deleteNotification(lastIndex);
		
		notifications = this.notificationService.findAllNotifications();
		int notificationsSizeAfter = notifications.size();
		
		assertThat(notificationsSizeAfter).isEqualTo(notificationsSizeBefore - 1);
	}

}
