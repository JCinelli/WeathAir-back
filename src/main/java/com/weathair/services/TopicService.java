package com.weathair.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.forum.TopicDto;
import com.weathair.dto.forum.TopicResponseDto;
import com.weathair.entities.forum.Topic;
import com.weathair.exceptions.TopicException;
import com.weathair.repositories.TopicRepository;

/**
 * @author MIACHELL
 *
 * Class TopicService for CRUD into Topic table
 *
 */
@Service
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
	public List<TopicResponseDto> findAllTopics() throws TopicException{
		List<Topic> topics = topicRepository.findAll();
		if (!topics.isEmpty()) {
			List<TopicResponseDto> topicResponseDtos = new ArrayList<>();
			for (Topic topic : topics) {
				topicResponseDtos.add(entityToDto(topic));
			}
			return topicResponseDtos;
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
	 * @param 			topic2
	 * @return			The saved topic
	 */
	public Topic createTopic(TopicResponseDto topic2) {
		Topic topic = new Topic();
		topic.setLabel(topic2.getLabel());
		return topicRepository.save(topic);
	}
	
	/**
	 * This method updates a topic
	 * 
	 * @param 			id the id of the topic to update
	 * @param 			topicDto the text to update
	 * @return			The saved topic
	 * @throws 			TopicException 
	 */
	public Topic updateTopic(Integer id, TopicDto topicDto) throws TopicException {
		Optional<Topic> topicOptToUpdate = topicRepository.findById(id);
		if (!topicOptToUpdate.isEmpty()) {
			Topic topicToUpdate = topicOptToUpdate.get();
			topicToUpdate.setLabel(topicDto.getLabel());
			return topicRepository.save(topicToUpdate);
		} else {
			throw new TopicException("No Topic with id " + id + " was found in the DB");
		}
	}
	
	/**
	 * This method deletes a topic
	 * 
	 * @param 			id the id of the Topic to update
	 * @throws 			TopicException
	 */
	public void deleteTopic(Integer id) throws TopicException{
		Optional<Topic> topicToDelete = topicRepository.findById(id);
		if (!topicToDelete.isEmpty()) {
			topicRepository.delete(topicToDelete.get());
		} else {
			throw new TopicException("No Topic with id " + id + " was found in the DB");
		}
		
	}
	
	private TopicResponseDto entityToDto (Topic topic) {
		TopicResponseDto topicDto = new TopicResponseDto();
		topicDto.setLabel(topic.getLabel());
		topicDto.setId(topic.getId());
		return topicDto;
	}
}
