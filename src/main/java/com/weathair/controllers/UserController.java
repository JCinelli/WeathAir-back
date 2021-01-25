package com.weathair.controllers;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weathair.dto.UserDto;
import com.weathair.exceptions.RepositoryException;
import com.weathair.exceptions.UserException;
import com.weathair.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/me")
	public ResponseEntity<?> getConnectedUser(Principal principal) {
		try {
			return ResponseEntity.ok().body(userService.getByEmail(principal.getName()));
		} catch (UserException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllUsers () throws UserException {
		return ResponseEntity.ok().body(userService.findAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById (Integer id) throws UserException {
		return ResponseEntity.ok().body(userService.findUserById(id));
	}
	
	
	@PostMapping
	public ResponseEntity<?> postUser (@Validated @RequestBody UserDto userDto, BindingResult resVal){
		if (!resVal.hasErrors()) {
			return ResponseEntity.ok().body(userService.createUser(userDto));
		} else {
			return ResponseEntity.badRequest().body(resVal.getAllErrors());
		}
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@PutMapping("/{id}")
	public ResponseEntity<?> putUser (@RequestParam Integer id, @RequestBody UserDto userDto) throws UserException{
		userService.updateUser(id, userDto);
		return ResponseEntity.ok("The user with id " + id + " has been successfully updated");
	}
	
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@PutMapping("/ban")
	public ResponseEntity<?> putBanUser (@RequestParam Integer id) throws UserException, RepositoryException{
		userService.banUser(id);
		return ResponseEntity.ok("The user with id " + id + " has been successfully banned");
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@PutMapping("/unban")
	public ResponseEntity<?> putUnBanUser (@RequestParam Integer id) throws UserException{
		userService.unBanUser(id);
		return ResponseEntity.ok("The user with id " + id + " has been successfully unban");
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser (@RequestParam Integer id) throws UserException {
		userService.deleteUser(id);
		return ResponseEntity.ok("The user with id " + id + " has been successfully deleted");
	}

}
