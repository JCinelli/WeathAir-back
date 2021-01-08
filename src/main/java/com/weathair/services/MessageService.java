package com.weathair.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.weathair.dto.forum.MessageDto;
import com.weathair.entities.User;
import com.weathair.entities.forum.Message;
import com.weathair.entities.forum.Post;
import com.weathair.exceptions.MessageException;
import com.weathair.repositories.MessageRepository;

/**
 * @author MIACHELL
 * 
 * Class MessageService for CRUD into Message table
 *
 */
public class MessageService {
	
	private MessageRepository messageRepository;

	public MessageService(MessageRepository messageRepository) {
		super();
		this.messageRepository = messageRepository;
	}
	
	/**
	 * This method finds all the messages in the DB
	 * 
	 * @return			List of message
	 * @throws 			MessageException
	 */
	public List<Message> findAllMessages() throws MessageException{
		List<Message> listMessages = messageRepository.findAll();
		if (!listMessages.isEmpty()) {
			return listMessages;
		} else {
			throw new MessageException("There is no Message in the DB");
		}
	}
	
	/**
	 * This method finds a message in the DB using an id
	 * 
	 * @return			Opt of message
	 * @throws 			MessageException 
	 */
	public Message findMessageById(Integer id) throws MessageException{
		Optional<Message> messageOptional = messageRepository.findById(id);
		if (messageOptional.isPresent()) {
			return messageOptional.get();
		} else {
			throw new MessageException("No Message with id " + id + " was found in the DB");
		}
	}
	
	/**
	 * This method creates a new message in the DB
	 * 
	 * @param 			messageDto
	 * @return			The saved message
	 */
	public Message createMessage(MessageDto messageDto) {
		Message message = new Message();
		message.setText(messageDto.getText());
		message.setDateTime(messageDto.getDateTime());
		//TODO replace by find by post id
		message.setPost(messageDto.getPost());
		//TODO replace by find by user id
		message.setUser(messageDto.getUser());
		return messageRepository.save(message);
	}
	
	/**
	 * This method updates the text in a Message
	 * 
	 * @param 			id the id of the Message to update
	 * @param 			newText the text to insert into the message
	 * @return			The saved Message
	 * @throws 			MessageException 
	 */
	public Message updateMessageText(Integer id, String newText) throws MessageException {
		Message messageToUpdate = findMessageById(id);
		messageToUpdate.setText(newText);
		return messageRepository.save(messageToUpdate);
	}
	
	/**
	 * This method updates the dateTime of a message
	 * 
	 * @param 			id the id of the Message to update
	 * @param 			newDateTime the dateTime to insert into the message
	 * @return			the saved Message
	 * @throws 			MessageException 
	 */
	public Message updateMessageDateTime(Integer id, LocalDateTime newDateTime) throws MessageException {
		Message messageToUpdate = findMessageById(id);
		messageToUpdate.setDateTime(newDateTime);
		return messageRepository.save(messageToUpdate);
	}
	
	/**
	 * This method updates the Post containing the message
	 * 
	 * @param 			id the id of the Message to update
	 * @param 			newPost the post of the message
	 * @return			the saved message
	 * @throws MessageException 
	 */
	public Message updateMessagePost(Integer id, Post newPost) throws MessageException {
		Message messageToUpdate = findMessageById(id);
		messageToUpdate.setPost(newPost);
		return messageRepository.save(messageToUpdate);
	}
	
	/**
	 * This method updates the User that posts the message
	 * 
	 * @param 			id the id of the Message to update
	 * @param 			newUser the user that posts the message
	 * @return			the saved message
	 * @throws 			MessageException 
	 */
	public Message updateMessageUser(Integer id, User newUser) throws MessageException {
		Message messageToUpdate = findMessageById(id);
		messageToUpdate.setUser(newUser);
		return messageRepository.save(messageToUpdate);
	}
	
	/**
	 * This method deletes a message
	 * 
	 * @param 			id the id of the Message to update
	 * @throws 			MessageException
	 */
	public void deleteMessage(Integer id) throws MessageException{
		Message messageToDelete = findMessageById(id);
		messageRepository.delete(messageToDelete);
	}

}
