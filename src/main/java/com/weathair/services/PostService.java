package com.weathair.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.forum.PostDto;
import com.weathair.dto.forum.PostResponseDto;
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
	 * @throws 			TopicException 
	 */
	public List<PostResponseDto> findAllPosts(Integer idTopic) throws PostException, TopicException{
		Topic topic = getTopicById(idTopic);
		List<Post> postList = postRepository.findByTopic(topic);
		if (!postList.isEmpty()) {
			List<PostResponseDto> postDtoList = new ArrayList<>();
			for(Post post: postList) {
				postDtoList.add(entityToDto(post));
			}
			return postDtoList;
		} else {
			throw new PostException("There is no Post in the DB");
		}
		
	}

	/**
	 * This method finds a post in the DB using an id
	 * 
	 * @return			post
	 * @throws 			PostException 
	 */
	public PostResponseDto findPostById(Integer idTopic, Integer id) throws PostException {
		Optional<Post> postOptional = postRepository.findById(id);
		if (postOptional.isPresent()) {
			Post post = postOptional.get();
			if (post.getTopic().getId() == idTopic) {
				return entityToDto(post);
			} else {
				throw new PostException("No Post with id " + id + " in topic with id " + idTopic + " was found in the DB");
			}
		} else {
			throw new PostException("No Post with id " + id + " was found in the DB");
		}
	}
	
	/**
	 * This method creates a new post in the DB
	 * 
	 * @param 			postDto
	 * @return			The saved post
	 * @throws 			UserException 
	 * @throws 			TopicException 
	 */
	public Post createPost(Integer idTopic, PostDto postDto) throws TopicException, UserException {
		Post post = new Post();
		dtoToEntity(post, postDto);
		//post.setTopic(getTopicById(idTopic));
		return postRepository.save(post);
	}
	
	/**
	 * This method updates a post
	 * 
	 * @param 			id the id of the post to update
	 * @param 			postDto the post to update into the DB
	 * @return			The saved post
	 * @throws 			PostException 
	 * @throws 			UserException 
	 * @throws 			TopicException 
	 */
	public Post updatePost(Integer idTopic, Integer id, PostDto postDto) throws PostException, TopicException, UserException {
		Optional<Post> findById = postRepository.findById(id);
		if (!findById.isEmpty()) {
			Post postToUpdate = findById.get();
			if (postToUpdate.getTopic().getId() == idTopic) {
				dtoToEntity(postToUpdate, postDto);
				return postRepository.save(postToUpdate);
			} else {
				throw new PostException("No Post with id " + id + " in topic with id " + idTopic + " was found in the DB");
			}
		} else {
			throw new PostException("No Post with id " + id + " was found in the DB");
		}
		
	}
	
	/**
	 * This method deletes a post
	 * 
	 * @param 			id the id of the post to update
	 * @throws 			PostException
	 */
	public void deletePost(Integer idTopic, Integer id) throws PostException {
		Optional<Post> findById = postRepository.findById(id);
		if (!findById.isEmpty()) {
			Post postToDelete = findById.get();
			if (postToDelete.getTopic().getId() == idTopic) {
				postRepository.save(postToDelete);
			} else {
				throw new PostException("No Post with id " + id + " in topic with id " + idTopic + " was found in the DB");
			}
		} else {
			throw new PostException("No Post with id " + id + " was found in the DB");
		}
	}
	
	private Post dtoToEntity (Post post, PostDto postDto) throws TopicException, UserException {
		post.setTitle(postDto.getTitle());
		post.setText(postDto.getText());
		post.setTopic(getTopicById(postDto.getTopicId()));
		post.setUser(getUserById(postDto.getUserId()));
		return post;
	}
	
	private PostResponseDto entityToDto (Post post) {
		PostResponseDto postDto = new PostResponseDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setText(post.getText());
		postDto.setTopic(post.getTopic());
		postDto.setUser(post.getUser());
		return postDto;
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
