package com.weathair.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.forum.MessageDto;
import com.weathair.entities.User;
import com.weathair.entities.forum.Message;
import com.weathair.entities.forum.Post;
import com.weathair.exceptions.MessageException;
import com.weathair.exceptions.PostException;
import com.weathair.exceptions.UserException;
import com.weathair.repositories.MessageRepository;
import com.weathair.repositories.PostRepository;
import com.weathair.repositories.UserRepository;

/**
 * @author MIACHELL
 * 
 * Class MessageService for CRUD into Message table
 *
 */
@Service
public class MessageService {
	
	private MessageRepository messageRepository;
	private PostRepository postRepository;
	private UserRepository userRepository;

	public MessageService(MessageRepository messageRepository, PostRepository postRepository, UserRepository userRepository) {
		super();
		this.messageRepository = messageRepository;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
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
	 * @throws PostException 
	 * @throws UserException 
	 */
	public Message createMessage(MessageDto messageDto) throws UserException, PostException {
		Message message = new Message();
		dtoToEntity(message, messageDto);
		return messageRepository.save(message);
	}

	/**
	 * This method updates a Message
	 * 
	 * @param 			id the id of the Message to update
	 * @param 			messageDto contains the attributes to insert into the message
	 * @return			The saved Message
	 * @throws 			MessageException 
	 * @throws PostException 
	 * @throws UserException 
	 */
	public Message updateMessage(Integer id, MessageDto messageDto) throws MessageException, UserException, PostException {
		Message messageToUpdate = findMessageById(id);
		dtoToEntity(messageToUpdate, messageDto);
		return messageRepository.save(messageToUpdate);
	}
	
	/**
	 * This method deletes a message
	 * 
	 * @param 			id the id of the Message to delete
	 * @throws 			MessageException
	 */
	public void deleteMessage(Integer id) throws MessageException{
		Message messageToDelete = findMessageById(id);
		messageRepository.delete(messageToDelete);
	}
	
	private Message dtoToEntity(Message message, MessageDto messageDto) throws UserException, PostException {
		message.setText(messageDto.getText());
		message.setUser(getUserById(messageDto.getUserId()));
		message.setPost(getPostById(messageDto.getPostId()));
		return message;
	}
	
	private Post getPostById(Integer id) throws PostException {
		Optional<Post> postOptional = postRepository.findById(id);
		if (!postOptional.isEmpty()) {
			return postOptional.get();
		} else {
			throw new PostException("No Post with id " + id + " has been found in DB");
		}
	}

	private User getUserById(Integer id) throws UserException {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isEmpty()) {
			return userOptional.get();
		} else {
			throw new UserException("No User with id " + id + " has been found in DB");
		}
	}
	
}
