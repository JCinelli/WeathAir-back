package com.weathair.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.weathair.entities.Township;
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
	 * @param file				the CSV file
	 * @throws IOException
	 */
	@Transactional
	public void saveTownShips(File file) throws IOException {
		
		List<String> fileLines = FileReader.readFile(file);
		List<Township> townShips = FileParser.parse(fileLines);
		
		for (Township township : townShips) {
			townshipRepository.save(township);
		}
	}
}
