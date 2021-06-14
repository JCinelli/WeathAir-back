package com.weathair.service;
import static org.assertj.core.api.Assertions.assertThat;

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
		int initialSize = topicService.findAllTopics().size();
		assertThat(initialSize).isEqualTo(4);	
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
		Topic topic2 = topicService.findTopicById(16);
		assertThat(topic2.getLabel()).isEqualTo(topic.getLabel());
	}
	
	@Test
	@Order(4)
	public void testUpdateTopic() throws TopicException {
		Topic topic = topicService.findTopicById(16);
		topic.setLabel("cest une modif sur l'id 16 aha");
		
		TopicDto topicDto = new TopicDto();
		topicDto.setLabel(topic.getLabel());
		
		topicService.updateTopic(2, topicDto);
		assertThat(topic.getLabel()).isEqualTo(topicDto.getLabel());
	}
	
	@Test
	@Order(5)
	public void testDeleteTopic() throws TopicException {
		int initialSize = topicService.findAllTopics().size();
		topicService.deleteTopic(16);
		assertThat(topicService.findAllTopics().size()).isEqualTo(initialSize-1);
	}
	
}
