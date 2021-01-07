package com.weathair.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 * This method saves all townships from the file in the database by calling the service
	 * 
	 * @param file
	 * @throws IOException
	 */
	@PostMapping
	public void postTownships(File file) throws IOException {
		townshipService.saveTownShips(file);
	}

}
