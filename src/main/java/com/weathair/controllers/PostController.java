package com.weathair.controllers;

import java.security.Principal;

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

import com.weathair.dto.forum.PostDto;
import com.weathair.entities.User;
import com.weathair.exceptions.PostException;
import com.weathair.exceptions.TopicException;
import com.weathair.exceptions.UserException;
import com.weathair.services.PostService;
import com.weathair.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/forum/topics/{idTopic}/posts")
public class PostController {
	
	private PostService postService;
	private UserService userService;
	
	public PostController(PostService postService, UserService userService) {
		super();
		this.postService = postService;
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllPosts (@PathVariable Integer idTopic) throws PostException, TopicException {
		return ResponseEntity.ok().body(postService.findAllPosts(idTopic));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPostById (@PathVariable Integer idTopic, @PathVariable Integer id) throws PostException {
		return ResponseEntity.ok().body(postService.findPostById(idTopic, id));
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getPostByTopics (@PathVariable Integer idTopic, @PathVariable Integer id) throws PostException {
//		return ResponseEntity.ok().body(postService.findPostById(idTopic, id));
//	} 
	
	//@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR') || hasAuthority('ROLE_USER')")
	@PostMapping
	public ResponseEntity<?> postPost (Principal principal, @PathVariable Integer idTopic, @Validated @RequestBody PostDto postDto,
			BindingResult resVal) throws TopicException, UserException {
		
		System.out.println(principal.getName());
		User user = userService.getByEmail(principal.getName());

		
		if (!resVal.hasErrors()) {
			
			return ResponseEntity.ok().body(postService.createPost(idTopic,  user.getId(), postDto));
		} else {
			return ResponseEntity.badRequest().body(resVal.getAllErrors());
		}
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@PutMapping("/{id}")
	public ResponseEntity<?> putPost(@PathVariable Integer idTopic, @PathVariable Integer id, @RequestBody PostDto postDto) throws PostException, TopicException, UserException{
		postService.updatePost(idTopic, id, postDto);
		return ResponseEntity.ok("The post with id " + id + " has been successfully updated");
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost (@PathVariable Integer idTopic, @PathVariable Integer id) throws PostException {
		postService.deletePost(idTopic, id);;
		return ResponseEntity.ok("The post with id " + id + " has been successfully deleted");
	}

}
