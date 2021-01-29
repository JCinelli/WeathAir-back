package com.weathair.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.forum.MessageDto;
import com.weathair.dto.forum.MessageDtoResponse;
import com.weathair.entities.User;
import com.weathair.entities.forum.Message;
import com.weathair.entities.forum.Post;
import com.weathair.exceptions.MessageException;
import com.weathair.exceptions.PostException;
import com.weathair.exceptions.TopicException;
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
	 * @throws PostException 
	 * @throws TopicException 
	 */
	public List<MessageDtoResponse> findAllMessages(Integer idPost) throws PostException, MessageException{
		Post post = getPostById(idPost);
	
		List<Message> listMessages = messageRepository.findByPost(post);
		if (!listMessages.isEmpty()) {
			List<MessageDtoResponse> messageDtoList  = new ArrayList<>();
			for(Message message : listMessages) {
				messageDtoList.add(entityToDto(message));
			}
			return messageDtoList;
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
	public Message createMessage(Integer idTopic, Integer idPost, Integer idUser, MessageDto messageDto) throws UserException, PostException {
		Message message = new Message();
		dtoToEntity(message, messageDto, idUser);
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
	 * @throws TopicException 
	 */
	public Message updateMessage(Integer idTopic, Integer idPost, Integer id, MessageDto messageDto) throws MessageException, UserException, PostException, TopicException {
		Message messageToUpdate = findMessageById(id);
		if (messageToUpdate.getPost().getId() == idPost && messageToUpdate.getPost().getTopic().getId() == idTopic) {
		//	dtoToEntity(messageToUpdate, messageDto);
			return messageRepository.save(messageToUpdate);
		} else if (messageToUpdate.getPost().getId() != idPost) {
			throw new PostException("No message with id " + id + " in Post with id " + idPost + " has been found in the DB");
		} else {
			throw new TopicException("No message with id " + id + " in Topic with id " + idTopic + " has been found in the DB");
		}
		
	}
	
	/**
	 * This method deletes a message
	 * 
	 * @param 			id the id of the Message to delete
	 * @throws 			MessageException
	 * @throws PostException 
	 * @throws TopicException 
	 */
	public void deleteMessage(Integer idTopic, Integer idPost, Integer id) throws MessageException, PostException, TopicException{
		Message messageToDelete = findMessageById(id);
		if (messageToDelete.getPost().getId() == idPost && messageToDelete.getPost().getTopic().getId() == idTopic) {
			messageRepository.delete(messageToDelete);
		} else if (messageToDelete.getPost().getId() != idPost) {
			throw new PostException("No message with id " + id + " in Post with id " + idPost + " has been found in the DB");
		} else {
			throw new TopicException("No message with id " + id + " in Topic with id " + idTopic + " has been found in the DB");
		}
	}
	
	private Message dtoToEntity(Message message, MessageDto messageDto, Integer userId) throws UserException, PostException {
		message.setText(messageDto.getText());
		message.setPost(getPostById(messageDto.getPostId()));
		message.setUser(getUserById(userId));
		return message;
	}
	
	private MessageDtoResponse entityToDto (Message message) {
		MessageDtoResponse messageDto = new MessageDtoResponse();
		messageDto.setId(message.getId());
		messageDto.setText(message.getText());
		messageDto.setPost(message.getPost());
		messageDto.setUser(message.getUser());
		messageDto.setDate_time(message.getDateTime());
		return messageDto;
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
