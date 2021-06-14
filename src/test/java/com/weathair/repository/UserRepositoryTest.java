package com.weathair.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.boot.test.context.SpringBootTest;

import com.weathair.entities.User;
import com.weathair.repositories.UserRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Test
	public void testFindByEmail() {
		Optional<User> user = userRepository.findByEmail("user@gmail.com");
				
		assertThat(user.get().getEmail().contains("user@gmail.com"));
	}
}
