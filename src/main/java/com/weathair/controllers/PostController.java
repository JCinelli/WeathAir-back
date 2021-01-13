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

import com.weathair.dto.forum.PostDto;
import com.weathair.exceptions.PostException;
import com.weathair.exceptions.TopicException;
import com.weathair.exceptions.UserException;
import com.weathair.services.PostService;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {
	
	private PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllPosts () throws PostException {
		return ResponseEntity.ok().body(postService.findAllPosts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPostById (@PathVariable Integer id) throws PostException {
		return ResponseEntity.ok().body(postService.findPostById(id));
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR') || hasAuthority('ROLE_USER')")
	@PostMapping
	public ResponseEntity<?> postPost (@Validated @RequestBody PostDto postDto, BindingResult resVal) throws TopicException, UserException {
		if (!resVal.hasErrors()) {
			return ResponseEntity.ok().body(postService.createPost(postDto));
		} else {
			return ResponseEntity.badRequest().body(resVal.getAllErrors());
		}
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@PutMapping("/{id}")
	public ResponseEntity<?> putPost(@PathVariable Integer id, @RequestBody PostDto postDto) throws PostException, TopicException, UserException{
		postService.updatePost(id, postDto);
		return ResponseEntity.ok("The post with id " + id + " has been successfully updated");
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost (@PathVariable Integer id) throws PostException {
		postService.deletePost(id);;
		return ResponseEntity.ok("The post with id " + id + " has been successfully deleted");
	}

}
