package com.weathair.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weathair.dto.forum.MessageDto;
import com.weathair.exceptions.MessageException;
import com.weathair.exceptions.PostException;
import com.weathair.exceptions.TopicException;
import com.weathair.exceptions.UserException;
import com.weathair.services.MessageService;

@RestController
@CrossOrigin
@RequestMapping("/forum/topics/{idTopic}/posts/{idPost}/messages")
public class MessageController {
	
	private MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllMessages (@PathVariable Integer idTopic,@PathVariable Integer idPost) throws MessageException, PostException {
		return ResponseEntity.ok().body(messageService.findAllMessages(idPost));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMessageById (@PathVariable Integer id) throws MessageException {
		return ResponseEntity.ok().body(messageService.findMessageById(id));
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR') || hasAuthority('ROLE_USER')")
	@PostMapping
	public ResponseEntity<?> postMessage (@Validated @RequestBody MessageDto messageDto, BindingResult resVal) throws UserException, PostException{
		if (!resVal.hasErrors()) {
			return ResponseEntity.ok().body(messageService.createMessage(messageDto));
		} else {
			return ResponseEntity.badRequest().body(resVal.getAllErrors());
		}
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@PutMapping("/{id}")
	public ResponseEntity<?> putMessage(Integer idTopic, Integer idPost, @PathVariable  Integer id, @RequestBody MessageDto messageDto) throws MessageException, UserException, PostException, TopicException{
		messageService.updateMessage(idTopic, idPost, id, messageDto);
		return ResponseEntity.ok("The message with id " + id + " has been successfully updated");
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMessage (Integer idTopic, Integer idPost, @PathVariable  Integer id) throws MessageException, PostException, TopicException {
		messageService.deleteMessage(idTopic, idPost, id);
		return ResponseEntity.ok("The message with id " + id + " has been successfully deleted");
	}

}
