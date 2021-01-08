package com.weathair.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.forum.PostDto;
import com.weathair.entities.forum.Post;
import com.weathair.exceptions.PostException;
import com.weathair.repositories.PostRepository;

/**
 * @author MIACHELL
 * 
 * Class PostService for CRUD into Post table 
 *
 */
@Service
public class PostService {
	
	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}
	
	/**
	 * This method finds all the posts in the DB
	 * 
	 * @return			List of posts
	 * @throws			PostException
	 */
	public List<Post> findAllPosts() throws PostException{
		List<Post> postList = postRepository.findAll();
		if (!postList.isEmpty()) {
			return postList;
		} else {
			throw new PostException("There is no Message in the DB");
		}
		
	}

	/**
	 * This method finds a post in the DB using an id
	 * 
	 * @return			post
	 * @throws 			PostException 
	 */
	public Post findPostById(Integer id) throws PostException{
		Optional<Post> postOptional = postRepository.findById(id);
		if (postOptional.isPresent()) {
			return postOptional.get();
		} else {
			throw new PostException("No Message with id " + id + " was found in the DB");
		}
	}
	
	/**
	 * This method creates a new post in the DB
	 * 
	 * @param 			postDto
	 * @return			The saved post
	 */
	public Post createPost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setText(postDto.getText());
		post.setDateTime(postDto.getDateTime());
		post.setMessages(postDto.getMessages());
		post.setTopic(postDto.getTopic());
		post.setUser(postDto.getUser());
		return postRepository.save(post);
	}
	
	/**
	 * This method updates a post
	 * 
	 * @param 			id the id of the post to update
	 * @param 			postDto the post to update into the DB
	 * @return			The saved post
	 * @throws 			PostException 
	 */
	public Post updatePost(Integer id, PostDto postDto) throws PostException {
		Post postToUpdate = findPostById(id);
		postToUpdate.setTitle(postDto.getTitle());
		postToUpdate.setText(postDto.getText());
		postToUpdate.setDateTime(postDto.getDateTime());;
		postToUpdate.setMessages(postDto.getMessages());
		postToUpdate.setTopic(postDto.getTopic());
		postToUpdate.setUser(postDto.getUser());
		return postRepository.save(postToUpdate);
	}
	
	/**
	 * This method deletes a post
	 * 
	 * @param 			id the id of the post to update
	 * @throws 			PostException
	 */
	public void deletePost(Integer id) throws PostException {
		Post postToDelete = findPostById(id);
		postRepository.delete(postToDelete);
	}

}
