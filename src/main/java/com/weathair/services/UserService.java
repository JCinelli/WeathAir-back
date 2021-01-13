package com.weathair.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.UserDto;
import com.weathair.entities.Role;
import com.weathair.entities.User;
import com.weathair.enumerations.RoleEnumeration;
import com.weathair.exceptions.RepositoryException;
import com.weathair.exceptions.UserException;
import com.weathair.repositories.RoleRepository;
import com.weathair.repositories.UserRepository;

/**
 * @author MIACHELL
 * 
 * Class UserService for CRUD into User table
 *
 */
@Service
public class UserService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	/**
	 * This method finds all the Users in the DB
	 * 
	 * @return			List of User
	 * @throws 			UserException
	 */
	public List<User> findAllUsers() throws UserException{
		List<User> listUsers = userRepository.findAll();
		if (!listUsers.isEmpty()) {
			return listUsers;
		} else {
			throw new UserException("There is no User in the DB");
		}
	}
	
	/**
	 * This method finds a User in the DB using an id
	 * 
	 * @return			User
	 * @throws 			UserException 
	 */
	public User findUserById(Integer id) throws UserException{
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return userOptional.get();
		} else {
			throw new UserException("No User with id " + id + " was found in the DB");
		}
	}
	
	/**
	 * This method creates a new user in the DB
	 * 
	 * @param 			userDto
	 * @return			The saved user
	 */
	public User createUser(UserDto userDto) {
		User user = new User();
		user.setPseudo(userDto.getPseudo());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setTownship(userDto.getTownship());
		user.setRole(userDto.getRole());
		return userRepository.save(user);
	}
	
	/**
	 * This method updates an user
	 * 
	 * @param 			id the id of the user to update
	 * @param 			userDto the user to update
	 * @return			The saved user
	 * @throws 			UserException 
	 */
	public User updateUser(Integer id, UserDto userDto) throws UserException {
		User userToUpdate = findUserById(id);
		userToUpdate.setPseudo(userDto.getPseudo());
		userToUpdate.setPassword(userDto.getPassword());
		userToUpdate.setEmail(userDto.getEmail());
		userToUpdate.setTownship(userDto.getTownship());
		return userRepository.save(userToUpdate);
	}
	
	/**
	 * @param id
	 * @return user ban by Id
	 * @throws UserException
	 * @throws RepositoryException 
	 */
	public User banUser(Integer id) throws UserException, RepositoryException {
		User userToBan = findUserById(id);
		userToBan.setRole(getRoleByLabel(RoleEnumeration.USER_BAN));
		return userRepository.save(userToBan);
	}
	
	/**
	 * @param id
	 * @return user unban by Id
	 * @throws UserException
	 */
	public User unBanUser (Integer id) throws UserException {
		User userToUnBan = findUserById(id);
		userToUnBan.setRole(getRoleByLabel(RoleEnumeration.USER));
		return userRepository.save(userToUnBan);
	}
	
	/**
	 * This method deletes an user
	 * 
	 * @param 			id the id of the User to delete
	 * @throws 			UserException
	 */
	public void deleteUser(Integer id) throws UserException {
		User userToDelete = findUserById(id);
		userRepository.delete(userToDelete);
	}

	/**
	 * This method extracts a role entity from DB
	 * 
	 * @param 			role
	 * @return			The role found in DB
	 * @throws 			UserException
	 */
	private Role getRoleByLabel(RoleEnumeration role) throws UserException {
		Optional<Role> findByLabel = roleRepository.findByLabel(role);
		if (!findByLabel.isEmpty()) {
			return findByLabel.get();
		} else {
			throw new UserException("Role " + role + " has not been found in DB");
		}
	}
	
		
	
}
