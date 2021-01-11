package com.weathair.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.AdministratorDto;
import com.weathair.entities.Administrator;
import com.weathair.exceptions.UserException;
import com.weathair.repositories.AdministratorRepository;

/**
 * @author MIACHELL
 * 
 * Class AdministratorService for CRUD into User table
 *
 */
@Service
public class AdministratorService {

	private AdministratorRepository administratorRepository;

	public AdministratorService(AdministratorRepository administratorRepository) {
		super();
		this.administratorRepository = administratorRepository;
	}
	
	/**
	 * This method finds all the administrators in the DB
	 * 
	 * @return			List of administrators
	 * @throws 			UserException
	 */
	public List<Administrator> findAllAdministrators() throws UserException{
		List<Administrator> listAdministrators = administratorRepository.findAll();
		if (!listAdministrators.isEmpty()) {
			return listAdministrators;
		} else {
			throw new UserException("There is no Administrator in the DB");
		}
	}
	
	/**
	 * This method finds an administrator in the DB using an id
	 * 
	 * @return			administrator
	 * @throws 			UserException 
	 */
	public Administrator findAdministratorById(Integer id) throws UserException{
		Optional<Administrator> administratorOptional = administratorRepository.findById(id);
		if (administratorOptional.isPresent()) {
			return administratorOptional.get();
		} else {
			throw new UserException("No Administrator with id " + id + " was found in the DB");
		}
	}
	
	/**
	 * This method creates a new administrator in the DB
	 * 
	 * @param 			administratorDto
	 * @return			The saved administrator
	 */
	public Administrator createAdministrator(AdministratorDto administratorDto) {
		Administrator administrator = new Administrator();
		administrator.setPseudo(administratorDto.getPseudo());
		administrator.setPassword(administratorDto.getPassword());
		administrator.setEmail(administratorDto.getEmail());
		administrator.setTownship(administratorDto.getTownship());
		return administratorRepository.save(administrator);
	}
	
	/**
	 * This method updates an administrators
	 * 
	 * @param 			id the id of the administrator to update
	 * @param 			administratorDto the administrator to update
	 * @return			The saved administrator
	 * @throws 			UserException 
	 */
	public Administrator updateAdministrator(Integer id, AdministratorDto administratorDto) throws UserException {
		Administrator administratorToUpdate = findAdministratorById(id);
		administratorToUpdate.setPseudo(administratorDto.getPseudo());
		administratorToUpdate.setPassword(administratorDto.getPassword());
		administratorToUpdate.setEmail(administratorDto.getEmail());
		administratorToUpdate.setTownship(administratorDto.getTownship());
		return administratorRepository.save(administratorToUpdate);
	}
	
	/**
	 * This method deletes an administrator
	 * 
	 * @param 			id the id of the administrator to delete
	 * @throws 			UserException
	 */
	public void deleteAdministrator(Integer id) throws UserException {
		Administrator administratorToDelete = findAdministratorById(id);
		administratorRepository.delete(administratorToDelete);
	}

}
