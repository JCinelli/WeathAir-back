package com.weathair.service;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.boot.test.context.SpringBootTest;
import com.weathair.exceptions.MessageException;
import com.weathair.exceptions.PostException;
import com.weathair.services.MessageService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MessageServiceTest {

	@Autowired
	private MessageService messageService;
	
	@Test
	public void testFindAllMessages() throws MessageException, PostException {
		int initialSize = messageService.findAllMessages(1).size();
		assertThat(initialSize).isEqualTo(4);
	}
}
