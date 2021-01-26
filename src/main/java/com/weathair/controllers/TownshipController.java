package com.weathair.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weathair.entities.Township;
import com.weathair.exceptions.TownshipException;
import com.weathair.services.TownshipService;

/**
 * @author jerem
 *
 * Controller for the townships
 *
 */
@CrossOrigin
@RestController
@RequestMapping("townships")
public class TownshipController {
	
//	FIELDS
	private TownshipService townshipService;

//	CONSTRUCTOR
	public TownshipController(TownshipService townshipService) {
		this.townshipService = townshipService;
	}
	
//	METHODS
	/**
	 * This method saves or update all townships from the file in the database by calling the service
	 * 
	 * @param file				the CSV file with all the townships
	 */
	
	public void postTownships(File file) throws IOException {
		townshipService.saveTownShips(file);
	}
	
	/**
	 * 
	 * This method saves or update the township in parameter in the database by calling the service
	 * 
	 * @param township			a township
	 */

	@PostMapping
	public void postTownship(Township township) {
		townshipService.saveTownShip(township);
	}
	
	/**
	 * 
	 * This method creates a township with the parameters and saves or update it in the database by calling the service
	 * 
	 * @param inseeCode			unique code of a township
	 * @param name				name of the township
	 * @param population		total population of the township
	 */
	@PostMapping("{inseeCode}")
	public void postTownship(String inseeCode, String name, int population) {
		townshipService.saveTownship(inseeCode, name, population);
	}
	
	/**
	 * @return a list of all the townships from the DB
	 * @throws TownshipException 
	 */
//	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@GetMapping
	public List<Township> getTownships() throws TownshipException {
		return townshipService.findAll();
	}
	
	/**
	 * @param inseeCode
	 * @return	the township in database by the insee code
	 * @throws TownshipException
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@GetMapping("/insee/{inseeCode}")
	public ResponseEntity<?> getTownshipByInseeCode(@RequestParam String inseeCode) throws TownshipException {
		Township township = townshipService.findByInseeCode(inseeCode);
		
		return ResponseEntity.ok(township);
	}
	
	/**
	 * @param name				the name of the township
	 * @return	the township(s) in database by name
	 * @throws TownshipException if any township in db has the name passed in parameter
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@GetMapping("name/{name}")
	public ResponseEntity<?> getTownshipByName(@RequestParam String name) throws TownshipException {
		List<Township> townshipsByName = townshipService.findByName(name);
		
		return ResponseEntity.ok(townshipsByName);
	}
	
	/**
	 * Update the name of a township by using insee code
	 * 
	 * @param inseeCode					unique code of the township
	 * @param newName					the new name for the township
	 * @throws TownshipException
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@PutMapping("name/{inseeCode}")
	public void updateTownshipName(@RequestParam String inseeCode, @RequestBody String newName) throws TownshipException {
		townshipService.updateTownshipName(inseeCode, newName);
	}
	
	/**
	 * Update the population of a township by using insee code
	 * 
	 * @param inseeCode					unique code of the township
	 * @param newPopulation				the new population for the township
	 * @throws TownshipException
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@PutMapping("population/{inseeCode}")
	public void updateTownshipPopulation(@RequestParam String inseeCode, @RequestBody int newPopulation) throws TownshipException {
		townshipService.updateTownshipPopulation(inseeCode, newPopulation);
	}
	
	/**
	 * Find the township by insee code and delete it if exist in DB
	 * 
	 * @param inseeCode				unique code of the township
	 * @throws TownshipException
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@DeleteMapping("{inseeCode}")
	public ResponseEntity<?> deleteTownship(@RequestParam String inseeCode) throws TownshipException {
		return ResponseEntity.ok(townshipService.deleteByInseeCode(inseeCode));
	}
}
