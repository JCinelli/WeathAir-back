package com.weathair.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weathair.entities.GpsCoordinate;
import com.weathair.entities.Township;
import com.weathair.exceptions.TownshipException;
import com.weathair.repositories.GpsCoordinateRepository;
import com.weathair.repositories.TownshipRepository;

@Service
@Transactional
public class GpsCoordinateService {
	
	private GpsCoordinateRepository gpsCoordinateRepository; 
	private TownshipRepository townshipRepository;
	
	public GpsCoordinateService(GpsCoordinateRepository gpsCoordinateRepository, TownshipRepository townshipRepository) {
		super();
		this.gpsCoordinateRepository = gpsCoordinateRepository;
		this.townshipRepository = townshipRepository;
	}

	public List<GpsCoordinate> getAllGpsCoordinates() throws TownshipException {
		List<GpsCoordinate> listGpsCoordinates = gpsCoordinateRepository.findAll(); 
		if (!listGpsCoordinates.isEmpty()) {
			return listGpsCoordinates;
		} else { 
			throw new TownshipException("No Gps Coordinate has been found in DB");
		}
	}
	
	public List<GpsCoordinate> getGpsCoordinatesByTownshipName(String townshipName) throws TownshipException{
		List<GpsCoordinate> listGpsCoordinates = gpsCoordinateRepository.findByTownshipName(townshipName); 
		if (!listGpsCoordinates.isEmpty()) {
			return listGpsCoordinates;
		} else { 
			throw new TownshipException("No Gps Coordinate with Township name " + townshipName + " has been found in DB");
		}
	}
	
	public GpsCoordinate getFromApiWaqi(String townshipName) throws JsonProcessingException, TownshipException {
        String token = "292826f99ed5cb9786dcb5b462297fa67201bca1";
        
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        
        HttpEntity<String> reponse = null;
        
        try {
            reponse = restTemplate.getForEntity("https://api.waqi.info/feed/" + townshipName + "/?token=" + token, String.class);
        } catch (Exception e) {
            System.err.println("Couldn't extract GPS coordinates for township " + townshipName + " " + e.getMessage());
        }
        
        JsonNode root = objectMapper.readTree(reponse.getBody());
        JsonNode data = root.path("data");
        
        GpsCoordinate gpsCoordinate = new GpsCoordinate();
        gpsCoordinate.setTownship(getTownshipByName(townshipName));
        gpsCoordinate.setLat(data.path("city").path("geo").get(0).asDouble());
        gpsCoordinate.setLng(data.path("city").path("geo").get(1).asDouble());
                
        return gpsCoordinate;
        
	}

	public void insertGpsCoordinatesFromApiWaqi() throws TownshipException, JsonProcessingException {
		System.out.println("Inserting GPS Coordinates into DB...");
		Long start = System.currentTimeMillis();
		List<Township> townships = getAllTownships();
		
		List<GpsCoordinate> gpsCoordinates = new ArrayList<>();
		for (Township township : townships) {
			gpsCoordinates.add(getFromApiWaqi(township.getName()));
		}
		
		Long stop = System.currentTimeMillis();
        String time = new SimpleDateFormat("mm:ss:SSS").format(new Date(stop - start));
		
        gpsCoordinateRepository.saveAll(gpsCoordinates);
        System.out.println("GPS Coordinates database updated in " + time);
	}
	
	private Township getTownshipByName(String name) throws TownshipException {
		List<Township> townships = townshipRepository.findByNameOrderByPopulationDesc(name);
		if (!townships.isEmpty()) {
			return townships.get(0);
		} else {
			throw new TownshipException("No Township with name " + name + " has been found in DB");
		}
	}
	
	private List<Township> getAllTownships() throws TownshipException {
		List<Township> townships = townshipRepository.findInAirIndicator();
		if (!townships.isEmpty()) {
			return townships;
		} else {
			throw new TownshipException("There are no townships in DB");
		}
	}
	
}
