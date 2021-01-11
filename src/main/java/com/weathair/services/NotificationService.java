package com.weathair.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.NotificationDto;
import com.weathair.entities.Notification;
import com.weathair.exceptions.NotificationException;
import com.weathair.repositories.NotificationRepository;

/**
 * @author MIACHELL
 * 
 * Class NotificationService for CRUD into Notification table
 *
 */
@Service
public class NotificationService {
	
	private NotificationRepository notificationRepository;

	public NotificationService(NotificationRepository notificationRepository) {
		super();
		this.notificationRepository = notificationRepository;
	}
	
	/**
	 * This method finds all the notifications in the DB
	 * 
	 * @return			List of notification
	 * @throws 			NotificationException
	 */
	public List<Notification> findAllNotifications() throws NotificationException{
		List<Notification> listNotifications = notificationRepository.findAll();
		if (!listNotifications.isEmpty()) {
			return listNotifications;
		} else {
			throw new NotificationException("There is no Notification in the DB");
		}
	}
	
	/**
	 * This method finds a notification in the DB using an id
	 * 
	 * @return			Opt of notification
	 * @throws 			NotificationException 
	 */
	public Notification findNotificationById(Integer id) throws NotificationException{
		Optional<Notification> notificationOptional = notificationRepository.findById(id);
		if (notificationOptional.isPresent()) {
			return notificationOptional.get();
		} else {
			throw new NotificationException("No Notification with id " + id + " was found in the DB");
		}
	}
	
	/**
	 * This method creates a new notification in the DB
	 * 
	 * @param 			notificationDto
	 * @return			The saved notification
	 */
	public Notification createNotification(NotificationDto notificationDto) {
		Notification notification = new Notification();
		notification.setLabel(notificationDto.getLabel());
		notification.setDateTime(notificationDto.getDateTime());
		notification.setUsers(notificationDto.getUsers());
		return notificationRepository.save(notification);
	}
	
	/**
	 * This method updates a notification
	 * 
	 * @param 			id the id of the notification to update
	 * @param 			notificationDto the notification
	 * @return			The saved notification
	 * @throws 			NotificationException 
	 */
	public Notification updateNotification(Integer id, NotificationDto notificationDto) throws NotificationException {
		Notification notificationToUpdate = findNotificationById(id);
		notificationToUpdate.setLabel(notificationDto.getLabel());
		notificationToUpdate.setDateTime(notificationDto.getDateTime());
		return notificationRepository.save(notificationToUpdate);
	}
	
	/**
	 * This method deletes a notification
	 * 
	 * @param 			id the id of the notification to delete
	 * @throws 			NotificationException
	 */
	public void deleteNotification(Integer id) throws NotificationException {
		Notification notificationToDelete = findNotificationById(id);
		notificationRepository.delete(notificationToDelete);
	}

}
