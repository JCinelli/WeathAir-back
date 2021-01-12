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
			
			if (parts[0].startsWith("09") ||
				parts[0].startsWith("11") ||
				parts[0].startsWith("12") ||
				parts[0].startsWith("30") ||
				parts[0].startsWith("31") ||
				parts[0].startsWith("32") ||
				parts[0].startsWith("34") ||
				parts[0].startsWith("46") ||
				parts[0].startsWith("48") ||
				parts[0].startsWith("65") ||
				parts[0].startsWith("66") ||
				parts[0].startsWith("81") ||
				parts[0].startsWith("82")) {
				Township newTownShip = new Township();
				newTownShip.setInseeCode(parts[0]);
				newTownShip.setName(parts[1].replaceAll("' ","'").replaceAll(" '", "'"));
				newTownShip.setPopulation(Integer.parseInt(parts[4]));
				
				townShips.add(newTownShip);	
			}
			
		}
		
		return townShips;
		
	}
	
}
