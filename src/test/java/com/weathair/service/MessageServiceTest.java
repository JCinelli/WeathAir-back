package com.weathair.service;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weathair.exceptions.MessageException;
import com.weathair.exceptions.PostException;
import com.weathair.services.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
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
