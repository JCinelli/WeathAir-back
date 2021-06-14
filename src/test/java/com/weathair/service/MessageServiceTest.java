package com.weathair.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.boot.test.context.SpringBootTest;

import com.weathair.dto.forum.MessageDto;
import com.weathair.dto.forum.MessageDtoResponse;
import com.weathair.entities.forum.Message;
import com.weathair.exceptions.MessageException;
import com.weathair.exceptions.PostException;
import com.weathair.exceptions.TopicException;
import com.weathair.exceptions.UserException;
import com.weathair.services.MessageService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MessageServiceTest {

	@Autowired
	private MessageService messageService;
	
	@Test
	@Order(1)
	public void testFindAllMessages() throws MessageException, PostException {
		List<MessageDtoResponse> messages = messageService.findAllMessages(1);
		assertThat(!messages.isEmpty());
	}
	
	@Test
	@Order(2)
	public void testFindMessagesById() throws PostException, TopicException, MessageException {
		Message message = messageService.findMessageById(13);
		assertThat(message.getText()).isEqualTo("C'est vraiment une application terrible, étonnant qu'elle soit gratuite, a notre plus grand bonheur !");
	}
	
	@Test
	@Order(3)
	public void testCreateMessage() throws PostException, UserException, MessageException {
		MessageDto messageDto = new MessageDto();
		messageDto.setPostId(1);
		messageDto.setText("Nouveau mesage");
		messageDto.setUserId(45);
		messageService.createMessage(1, 1, 45, messageDto);
		
		List<MessageDtoResponse> messages = messageService.findAllMessages(1);
		int lastIndex = messages.get(messages.size() - 1).getId();
		Message message = messageService.findMessageById(lastIndex);
		assertThat(message.getId()).isEqualTo(lastIndex);
	}
	
	@Test
	@Order(4)
	public void testUpdateMessage() throws MessageException, PostException, TopicException, UserException {
		List<MessageDtoResponse> messages = messageService.findAllMessages(1);
		int lastIndex = messages.get(messages.size() - 1).getId();
		Message message = messageService.findMessageById(lastIndex);
		message.setText("modification du texte du message");
		MessageDto messageDto = new MessageDto();
		messageDto.setText(message.getText());
		messageService.updateMessage(1, 1, lastIndex, messageDto);
		assertThat(message.getText()).isEqualTo(messageDto.getText());
	}
	
	@Test
	@Order(5)
	public void testDeleteMessage() throws PostException, MessageException, TopicException {
		List<MessageDtoResponse> messages = messageService.findAllMessages(1);
		int lastIndex = messages.get(messages.size() - 1).getId();
		int initialSize = messageService.findAllMessages(1).size();
		messageService.deleteMessage(1, 1, lastIndex);
		assertThat(messageService.findAllMessages(1).size()).isEqualTo(initialSize-1);
	}
}
