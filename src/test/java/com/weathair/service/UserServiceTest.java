package com.weathair.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.weathair.dto.UserDto;
import com.weathair.entities.User;
import com.weathair.exceptions.RepositoryException;
import com.weathair.exceptions.UserException;
import com.weathair.services.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	@Order(1)
	public void testFindAllUsers() throws UserException {
		int initialSize = userService.findAllUsers().size();
		assertThat(initialSize).isEqualTo(5);
	}
	
	@Test
	@Order(2)
	public void testFindUserById() throws UserException {
		User user = userService.findUserById(1);
		assertThat(user.getPseudo()).isEqualTo("admin");
	}
	
	@Test
	@Order(3)
	public void testFindUserByEmail() throws UserException {
		User user = userService.findUserByEmail("administrator@gmail.com");
		assertThat(user.getPseudo()).isEqualTo("admin");
	}
	
	@Test
	@Order(4)
	public void testCreateUser() throws UserException {
		UserDto user1 = new UserDto();
		user1.setEmail("test1@test.fr");
		user1.setPseudo("test");
		user1.setPassword("password");
		userService.createUser(user1);
		
		List<User> users = userService.findAllUsers();
		int lastIndex = users.get(users.size() - 1).getId();
		
		User user = userService.findUserById(lastIndex);
		assertThat(user.getPseudo()).isEqualTo(user1.getPseudo());
	}
	
	@Test
	@Order(5)
	public void testUpdateUser() throws UserException {
		List<User> users = userService.findAllUsers();
		int lastIndex = users.get(users.size() - 1).getId();
		User user = userService.findUserById(lastIndex);
		user.setPseudo("toto");
		
		UserDto userDto = new UserDto();
		userDto.setPseudo(user.getPseudo());
		
		userService.updateUser(lastIndex, userDto);
		assertThat(user.getPseudo()).isEqualTo(userDto.getPseudo());
	}
	
	@Test
	@Order(6)
	public void testDeleteUser() throws UserException {
		List<User> users = userService.findAllUsers();
		int lastIndex = users.get(users.size() - 1).getId();
		int initialSize = userService.findAllUsers().size();
		userService.deleteUser(lastIndex);
		assertThat(userService.findAllUsers().size()).isEqualTo(initialSize-1);
	}
	
	@Test
	@Order(7)
	public void testBanUser() throws UserException, RepositoryException {
		List<User> users = userService.findAllUsers();
		int lastIndex = users.get(users.size() - 1).getId();
		userService.banUser(lastIndex);
		User user = userService.findUserById(lastIndex);
		assertThat(user.getRole().getId()).isEqualTo(3);
	}
	
	@Test
	@Order(8)
	public void testUnBanUser() throws UserException, RepositoryException {
		List<User> users = userService.findAllUsers();
		int lastIndex = users.get(users.size() - 1).getId();
		userService.unBanUser(lastIndex);
		User user = userService.findUserById(lastIndex);
		assertThat(user.getRole().getId()).isEqualTo(2);
	}
	
}
