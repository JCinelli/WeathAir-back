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

import com.weathair.entities.User;
import com.weathair.exceptions.UserException;
import com.weathair.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testFindAllUsers() throws UserException {
		List<User> users = userService.findAllUsers();
		assertThat(!users.isEmpty());
	}
	
	@Test
	public void testFindUserById() throws UserException {
		User user = userService.findUserById(1);
		
		assertThat(user.getPseudo()).isEqualTo("totomania");
	}
	
//	@Test 
//	public void testCreateUser() throws UserException {
//		UserDto user1 = new UserDto();
//		user1.setEmail("test1@test.fr");
//		user1.setPseudo("test");
//		
//		userService.createUser(user1);
//		
//		User user2 = userService.findUserByEmail("test1@test.fr");
//		
//		assertThat(user1.getPseudo()).isEqualTo(user2.getPseudo());
//	
//	}
	
	@Test
	public void testUpdateUser() throws UserException {
		User user = userService.findUserById(4);
		
		user.setPseudo("toto");
		
		User userUpdate = userService.findUserById(4);
		
		assertThat(userUpdate.getPseudo()).isEqualTo("toto");
	}
	
	
	@Test
	public void testDeleteUser() throws UserException {
		
		User user = userService.findUserById(4);
		userService.deleteUser(4);
		
		// assertThat(user).isNull();
		assertThat(user.getEmail().isEmpty());
	
	}
}
