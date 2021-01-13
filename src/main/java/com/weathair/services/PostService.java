package com.weathair.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.forum.PostDto;
import com.weathair.entities.User;
import com.weathair.entities.forum.Post;
import com.weathair.entities.forum.Topic;
import com.weathair.exceptions.PostException;
import com.weathair.exceptions.TopicException;
import com.weathair.exceptions.UserException;
import com.weathair.repositories.PostRepository;
import com.weathair.repositories.TopicRepository;
import com.weathair.repositories.UserRepository;

/**
 * @author MIACHELL
 * 
 * Class PostService for CRUD into Post table 
 *
 */
@Service
public class PostService {
	
	private PostRepository postRepository;
	private TopicRepository topicRepository;
	private UserRepository userRepository;

	public PostService(PostRepository postRepository, TopicRepository topicRepository, UserRepository userRepository) {
		super();
		this.postRepository = postRepository;
		this.topicRepository = topicRepository;
		this.userRepository = userRepository;
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
	 * @throws UserException 
	 * @throws TopicException 
	 */
	public Post createPost(PostDto postDto) throws TopicException, UserException {
		Post post = new Post();
		dtoToEntity(post, postDto);
		return postRepository.save(post);
	}
	
	/**
	 * This method updates a post
	 * 
	 * @param 			id the id of the post to update
	 * @param 			postDto the post to update into the DB
	 * @return			The saved post
	 * @throws 			PostException 
	 * @throws UserException 
	 * @throws TopicException 
	 */
	public Post updatePost(Integer id, PostDto postDto) throws PostException, TopicException, UserException {
		Post postToUpdate = findPostById(id);
		dtoToEntity(postToUpdate, postDto);
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
	
	private Post dtoToEntity (Post post, PostDto postDto) throws TopicException, UserException {
		post.setTitle(postDto.getTitle());
		post.setText(postDto.getText());
		post.setTopic(getTopicById(postDto.getTopicId()));
		post.setUser(getUserById(postDto.getUserId()));
		return post;
	}
	
	private Topic getTopicById(Integer id) throws TopicException {
		Optional<Topic> topicOptional = topicRepository.findById(id);
		if (!topicOptional.isEmpty()) {
			return topicOptional.get();
		} else {
			throw new TopicException("No Topic with id " + id + " has been found in DB");
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
