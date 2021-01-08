package com.weathair.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.weathair.dto.forum.PostDto;
import com.weathair.entities.User;
import com.weathair.entities.forum.Message;
import com.weathair.entities.forum.Post;
import com.weathair.entities.forum.Topic;
import com.weathair.exceptions.PostException;
import com.weathair.repositories.PostRepository;

/**
 * @author MIACHELL
 * 
 * Class PostService for CRUD into Post table 
 *
 */
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
	 * This method updates the title of a post
	 * 
	 * @param 			id the id of the post to update
	 * @param 			newTitle the title to insert into the post
	 * @return			The saved post
	 * @throws 			PostException 
	 */
	public Post updatePostTitle(Integer id, String newTitle) throws PostException {
		Post postToUpdate = findPostById(id);
		postToUpdate.setTitle(newTitle);
		return postRepository.save(postToUpdate);
	}
	
	/**
	 * This method updates the text of a post
	 * 
	 * @param 			id the id of the post to update
	 * @param 			newText the text to insert into the post
	 * @return			The saved post
	 * @throws 			PostException 
	 */
	public Post updatePostText(Integer id, String newText) throws PostException {
		Post postToUpdate = findPostById(id);
		postToUpdate.setText(newText);
		return postRepository.save(postToUpdate);
	}
	
	/**
	 * This method updates the dateTime of a post
	 * 
	 * @param 			id the id of the post to update
	 * @param 			newDateTime the dateTime to insert into the post
	 * @return			The saved post
	 * @throws 			PostException 
	 */
	public Post updatePostDateTime(Integer id, LocalDateTime newDateTime) throws PostException {
		Post postToUpdate = findPostById(id);
		postToUpdate.setDateTime(newDateTime);;
		return postRepository.save(postToUpdate);
	}
	
	/**
	 * This method updates the messages of a post
	 * 
	 * @param 			id the id of the post to update
	 * @param 			newMessages the text to insert into the post
	 * @return			The saved post
	 * @throws 			PostException 
	 */
	public Post updatePostMessages(Integer id, List<Message> newMessages) throws PostException {
		Post postToUpdate = findPostById(id);
		postToUpdate.setMessages(newMessages);
		return postRepository.save(postToUpdate);
	}
	
	/**
	 * This method updates the topic of a post
	 * 
	 * @param 			id the id of the post to update
	 * @param 			newTopic the topic to insert into the post
	 * @return			The saved post
	 * @throws 			PostException 
	 */
	public Post updatePostTopic(Integer id, Topic newTopic) throws PostException {
		Post postToUpdate = findPostById(id);
		postToUpdate.setTopic(newTopic);
		return postRepository.save(postToUpdate);
	}
	
	/**
	 * This method updates the text of a post
	 * 
	 * @param 			id the id of the post to update
	 * @param 			newText the text to insert into the post
	 * @return			The saved post
	 * @throws 			PostException 
	 */
	public Post updatePostUser(Integer id, User newUser) throws PostException {
		Post postToUpdate = findPostById(id);
		postToUpdate.setUser(newUser);
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
