package com.weathair.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.weathair.entities.Township;
import com.weathair.exceptions.TownshipException;
import com.weathair.repositories.TownshipRepository;
import com.weathair.utils.FileParser;
import com.weathair.utils.FileReader;

/**
 * @author jerem
 *
 * Service for the townships
 */
@Service
public class TownshipService {
//	FIELDS
	private TownshipRepository townshipRepository;

//	CONSTRUCTOR
	public TownshipService(TownshipRepository townshipRepository) {
		this.townshipRepository = townshipRepository;
	}

//	METHODS
	/**
	 * This method read ands parses the file. After, it saves all the townships in the database
	 * by calling the repository
	 * 
	 * @param file					The csv file
	 * @throws IOException			The method "readFile(file)" can throws an IOException
	 */
	@Transactional
	public void saveTownShips(File file) throws IOException {
		
		List<String> fileLines = FileReader.readFile(file);
		List<Township> townShips = FileParser.parse(fileLines);
		
		for (Township township : townShips) {
			townshipRepository.save(township);
		}
		
		System.out.println("All Townships saved in DB");
	}
	
	/**
	 * Save one township in the database
	 * 
	 * @param township			A township
	 */
	@Transactional
	public void saveTownShip(Township township) {
			townshipRepository.save(township);
	}
	
	
	/**
	 * It creates a township with params and saves it in the DB
	 * 
	 * @param inseeCode          unique code of the township	
	 * @param name				 name of the township
	 * @param population		 total population of the township
	 */
	@Transactional
	public void saveTownship(String inseeCode, String name, int population) {
		Township newTownship = new Township();
		newTownship.setInseeCode(inseeCode);
		newTownship.setName(name);
		newTownship.setPopulation(population);
		
		townshipRepository.save(newTownship);
	}
	
	/**
	 * @return all the townships from the database
	 * @throws TownshipException 
	 */
	public List<Township> findAll() throws TownshipException {
		
		List<Township> townships = townshipRepository.findAll();
		
		if (!townships.isEmpty()) {
			return townships;
		} else {
			throw new TownshipException("No townships in database ..");
		}
		
	}
	
	/**
	 * This method searchs a township by insee code and returns it if it's present 
	 * 
	 * @param inseeCode						unique code of the township
	 * @return								a township
	 * @throws TownshipException			if there is no township with this insee code
	 */
	public Township findByInseeCode(String inseeCode) throws TownshipException {
		Optional<Township> optTownship = townshipRepository.findById(inseeCode);
		
		if (optTownship.isPresent()) {
			return optTownship.get();
		} else {
			throw new TownshipException("No township with this Insee code in the database");
		}
	}
	
	/**
	 * This method searchs townships by name and returns it if it's present
	 * 
	 * @param name							name of the township
	 * @return								township(s)
	 * @throws TownshipException			if there is no township with this name
	 */
	public List<Township> findByName(String name) throws TownshipException {
		List<Township> townshipsByName = townshipRepository.findByName(name);
		
		if (!townshipsByName.isEmpty()) {
			return townshipsByName;
		} else {
			throw new TownshipException("No township with this name in the database");
		}
	}
	
	/**
	 * Update the name of a township by using insee code
	 * 
	 * @param inseeCode					unique code of the township
	 * @param newName					the new name for the township
	 * @throws TownshipException
	 */
	public void updateTownshipName(String inseeCode, String newName) throws TownshipException {
		Township township = this.findByInseeCode(inseeCode);
		township.setName(newName);
		
		townshipRepository.save(township);
	}
	
	/**
	 * Update the population of a township by using insee code
	 * 
	 * @param inseeCode					unique code of the township
	 * @param newPopulation				the new population for the township
	 * @throws TownshipException
	 */
	public void updateTownshipPopulation(String inseeCode, int newPopulation) throws TownshipException {
		Township township = this.findByInseeCode(inseeCode);
		township.setPopulation(newPopulation);
		
		townshipRepository.save(township);
	}
	
	/**
	 * Find the township by insee code and delete it if exist in DB
	 * 
	 * @param inseeCode				unique code of the township
	 * @throws TownshipException
	 */
	public String deleteByInseeCode(String inseeCode) throws TownshipException {
		
		Township township = this.findByInseeCode(inseeCode);
		
		townshipRepository.delete(township);
		
		return "Township deleted successfully !";
		
	}
}
