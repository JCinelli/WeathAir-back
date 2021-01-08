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

import com.weathair.dto.forum.PostDto;
import com.weathair.exceptions.PostException;
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
	public ResponseEntity<?> getPostById (Integer id) throws PostException {
		return ResponseEntity.ok().body(postService.findPostById(id));
	}
	
	@PostMapping
	public ResponseEntity<?> postPost (@Validated @RequestBody PostDto postDto, BindingResult resVal) {
		if (!resVal.hasErrors()) {
			return ResponseEntity.ok().body(postService.createPost(postDto));
		} else {
			return ResponseEntity.badRequest().body(resVal.getAllErrors());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putPost(@RequestParam Integer id, @RequestBody PostDto postDto) throws PostException{
		postService.updatePost(id, postDto);
		return ResponseEntity.ok("The post with id " + id + " has been successfully updated");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost (@RequestParam Integer id) throws PostException {
		postService.deletePost(id);;
		return ResponseEntity.ok("The post with id " + id + " has been successfully deleted");
	}

}
