package com.weathair.services;

import java.util.List;
import java.util.Optional;

import com.weathair.dto.forum.TopicDto;
import com.weathair.entities.forum.Post;
import com.weathair.entities.forum.Topic;
import com.weathair.exceptions.TopicException;
import com.weathair.repositories.TopicRepository;

/**
 * @author MIACHELL
 *
 * Class TopicService for CRUD into Topic table
 *
 */
public class TopicService {
	
	private TopicRepository topicRepository;

	public TopicService(TopicRepository topicRepository) {
		super();
		this.topicRepository = topicRepository;
	}
	
	/**
	 * This method finds all the topics in the DB
	 * 
	 * @return			List of topics
	 * @throws			TopicException
	 */
	public List<Topic> findAllTopics() throws TopicException{
		List<Topic> topicList = topicRepository.findAll();
		if (!topicList.isEmpty()) {
			return topicList;
		} else {
			throw new TopicException("There is no Topic in the DB");
		}
		
	}

	/**
	 * This method finds a topic in the DB using an id
	 * 
	 * @return			topic
	 * @throws 			TopicException 
	 */
	public Topic findTopicById(Integer id) throws TopicException{
		Optional<Topic> topicOptional = topicRepository.findById(id);
		if (topicOptional.isPresent()) {
			return topicOptional.get();
		} else {
			throw new TopicException("No Topic with id " + id + " was found in the DB");
		}
	}
	
	/**
	 * This method creates a new topic in the DB
	 * 
	 * @param 			topicDto
	 * @return			The saved topic
	 */
	public Topic createTopic(TopicDto topicDto) {
		Topic topic = new Topic();
		topic.setLabel(topicDto.getLabel());
		//TODO replace by find by post id
		topic.setPosts(topicDto.getPosts());
		return topicRepository.save(topic);
	}
	
	/**
	 * This method updates the label of a topic
	 * 
	 * @param 			id the id of the topic to update
	 * @param 			newText the text to insert into the topic
	 * @return			The saved topic
	 * @throws 			TopicException 
	 */
	public Topic updateTopicLabel(Integer id, String newLabel) throws TopicException {
		Topic topicToUpdate = findTopicById(id);
		topicToUpdate.setLabel(newLabel);
		return topicRepository.save(topicToUpdate);
	}
	
	/**
	 * This method updates the label of a topic
	 * 
	 * @param 			id the id of the topic to update
	 * @param 			newText the text to insert into the topic
	 * @return			The saved topic
	 * @throws 			TopicException 
	 */
	public Topic updateTopicPosts(Integer id, List<Post> newPosts) throws TopicException {
		Topic topicToUpdate = findTopicById(id);
		topicToUpdate.setPosts(newPosts);;
		return topicRepository.save(topicToUpdate);
	}
	
	/**
	 * This method deletes a topic
	 * 
	 * @param 			id the id of the Topic to update
	 * @throws 			TopicException
	 */
	public void deleteTopic(Integer id) throws TopicException{
		Topic topicToDelete = findTopicById(id);
		topicRepository.delete(topicToDelete);
	}
}
