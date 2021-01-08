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

import com.weathair.dto.forum.TopicDto;
import com.weathair.exceptions.TopicException;
import com.weathair.services.TopicService;

@RestController
@CrossOrigin
@RequestMapping("/topics")
public class TopicController {
	
	private TopicService topicService;

	public TopicController(TopicService topicService) {
		super();
		this.topicService = topicService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllTopics () throws TopicException {
		return ResponseEntity.ok().body(topicService.findAllTopics());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTopicById (Integer id) throws TopicException {
		return ResponseEntity.ok().body(topicService.findTopicById(id));
	}
	
	@PostMapping
	public ResponseEntity<?> postTopic (@Validated @RequestBody TopicDto topicDto, BindingResult resVal){
		if (!resVal.hasErrors()) {
			return ResponseEntity.ok().body(topicService.createTopic(topicDto));
		} else {
			return ResponseEntity.badRequest().body(resVal.getAllErrors());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putTopic(@RequestParam Integer id, TopicDto topicDto) throws TopicException{
		topicService.updateTopic(id, topicDto);
		return ResponseEntity.ok("The topic with id " + id + " has been successfully updated");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTopic (@RequestParam Integer id) throws TopicException {
		topicService.deleteTopic(id);
		return ResponseEntity.ok("The topic with id " + id + " has been successfully deleted");
	}

}
