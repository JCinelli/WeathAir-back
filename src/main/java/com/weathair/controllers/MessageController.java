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

import com.weathair.dto.forum.MessageDto;
import com.weathair.exceptions.MessageException;
import com.weathair.services.MessageService;

@RestController
@CrossOrigin
@RequestMapping("/messages")
public class MessageController {
	
	private MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllMessages () throws MessageException {
		return ResponseEntity.ok().body(messageService.findAllMessages());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMessageById (Integer id) throws MessageException {
		return ResponseEntity.ok().body(messageService.findMessageById(id));
	}
	
	@PostMapping
	public ResponseEntity<?> postMessage (@Validated @RequestBody MessageDto messageDto, BindingResult resVal){
		if (!resVal.hasErrors()) {
			return ResponseEntity.ok().body(messageService.createMessage(messageDto));
		} else {
			return ResponseEntity.badRequest().body(resVal.getAllErrors());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putMessage(@RequestParam Integer id, @RequestBody MessageDto messageDto) throws MessageException{
		messageService.updateMessage(id, messageDto);
		return ResponseEntity.ok("The message with id " + id + " has been successfully updated");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMessage (@RequestParam Integer id) throws MessageException {
		messageService.deleteMessage(id);
		return ResponseEntity.ok("The message with id " + id + " has been successfully deleted");
	}

}