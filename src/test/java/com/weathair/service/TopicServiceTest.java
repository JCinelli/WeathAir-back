package com.weathair.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weathair.dto.forum.TopicResponseDto;
import com.weathair.entities.forum.Topic;
import com.weathair.exceptions.TopicException;
import com.weathair.services.TopicService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TopicServiceTest {

	@Autowired
	private TopicService topicService;
	
	@Test
	public void testFindAllTopics() throws TopicException {
		List<TopicResponseDto> topics = topicService.findAllTopics();
		assertThat(!topics.isEmpty());
	}
	
	@Test
	public void testFindTopicById() throws  TopicException {
		Topic topic = topicService.findTopicById(1);
		assertThat(topic.getLabel()).isEqualTo("L'histoire de la météo et de l'air");
	}
	
	@Test 
	public void testCreateTopic() throws TopicException {
		TopicResponseDto topic = new TopicResponseDto();
		topic.setLabel("Et c'est un new label");
		topicService.createTopic(topic);
		Topic topic2 = topicService.findTopicById(14);
		assertThat(topic2.getLabel()).isEqualTo("Et c'est new label");
	}
	
	@Test
	public void testUpdateTopic() throws TopicException {
		Topic topic = topicService.findTopicById(14);
		topic.setLabel("cest une modif messieurs dames");
		Topic topicUpdate = topicService.findTopicById(14);
		assertThat(topicUpdate.getLabel()).isEqualTo("cest une modif messieurs dames");
	}
	
	@Test
	public void testDeleteTopic() throws TopicException {
		int initialSize = topicService.findAllTopics().size();
		topicService.deleteTopic(14);
		assertThat(topicService.findAllTopics().size()+1).isEqualTo(initialSize);
	}
	
}
