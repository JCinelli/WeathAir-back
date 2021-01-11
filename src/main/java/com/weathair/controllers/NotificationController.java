package com.weathair.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weathair.dto.NotificationDto;
import com.weathair.exceptions.NotificationException;
import com.weathair.services.NotificationService;

@RestController
@CrossOrigin
@RequestMapping("/notifications")
public class NotificationController {
	
	private NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllNotifications () throws NotificationException {
		return ResponseEntity.ok().body(notificationService.findAllNotifications());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getNotificationById (Integer id) throws NotificationException {
		return ResponseEntity.ok().body(notificationService.findNotificationById(id));
	}
	
	@PostMapping
	public ResponseEntity<?> postNotification (@Validated @RequestBody NotificationDto notificationDto, BindingResult resVal){
		if (!resVal.hasErrors()) {
			return ResponseEntity.ok().body(notificationService.createNotification(notificationDto));
		} else {
			return ResponseEntity.badRequest().body(resVal.getAllErrors());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putNotification (@RequestParam Integer id, @RequestBody NotificationDto notificationDto) throws NotificationException {
		notificationService.updateNotification(id, notificationDto);
		return ResponseEntity.ok("The Notification with id " + id + " has been successfully updated");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNotification (@RequestParam Integer id) throws NotificationException {
		notificationService.deleteNotification(id);
		return ResponseEntity.ok("The Notification with id " + id + " has been successfully deleted");
	}
	
}
