package com.weathair.utils;

import java.util.ArrayList;
import java.util.List;

import com.weathair.entities.Township;

public class FileParser {

	public static List<Township> parse(List<String> fileLines) {
		
		List<Township> townShips = new ArrayList<>();
		
		fileLines.remove(0);
		
		for (String line : fileLines) {
			
			String[] parts = line.split(";");
			
			Township newTownShip = new Township();
			newTownShip.setInseeCode(parts[0]);
			newTownShip.setName(parts[1]);
			newTownShip.setPopulation(Integer.parseInt(parts[4]));
			
			townShips.add(newTownShip);
			
		}
		
		return townShips;
		
	}
	
}
