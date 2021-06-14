package com.weathair.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.weathair.dto.forum.TopicDto;
import com.weathair.dto.forum.TopicResponseDto;
import com.weathair.entities.forum.Topic;
import com.weathair.exceptions.TopicException;
import com.weathair.services.TopicService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TopicServiceTest {

	@Autowired
	private TopicService topicService;
	
	@Test
	@Order(1)
	public void testFindAllTopics() throws TopicException {
		List<TopicResponseDto> topics = topicService.findAllTopics();
		assertThat(!topics.isEmpty());
	}
	
	@Test
	@Order(2)
	public void testFindTopicById() throws  TopicException {
		Topic topic = topicService.findTopicById(1);
		assertThat(topic.getLabel()).isEqualTo("L'histoire de la météo et de l'air");
	}
	
	@Test 
	@Order(3)
	public void testCreateTopic() throws TopicException {
		TopicResponseDto topic = new TopicResponseDto();
		topic.setLabel("Et c'est un new label");
		topicService.createTopic(topic);
		List<TopicResponseDto> topics = topicService.findAllTopics();
		int lastIndex = topics.get(topics.size() - 1).getId();
		Topic topic2 = topicService.findTopicById(lastIndex);
		assertThat(topic2.getLabel()).isEqualTo(topic.getLabel());
	}
	
	@Test
	@Order(4)
	public void testUpdateTopic() throws TopicException {
		List<TopicResponseDto> topics = topicService.findAllTopics();
		int lastIndex = topics.get(topics.size() - 1).getId();
		Topic topic = topicService.findTopicById(lastIndex);
		topic.setLabel("cest une modif sur l'id 16 aha");
		
		TopicDto topicDto = new TopicDto();
		topicDto.setLabel(topic.getLabel());
		
		topicService.updateTopic(2, topicDto);
		assertThat(topic.getLabel()).isEqualTo(topicDto.getLabel());
	}
	
	@Test
	@Order(5)
	public void testDeleteTopic() throws TopicException {
		List<TopicResponseDto> topics = topicService.findAllTopics();
		int lastIndex = topics.get(topics.size() - 1).getId();
		int initialSize = topicService.findAllTopics().size();
		topicService.deleteTopic(lastIndex);
		assertThat(topicService.findAllTopics().size()).isEqualTo(initialSize-1);
	}
	
}
