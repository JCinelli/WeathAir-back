package com.weathair.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weathair.dto.indicators.AirIndicatorDto;
import com.weathair.entities.Township;
import com.weathair.entities.indicators.AirIndicator;
import com.weathair.exceptions.AirIndicatorException;
import com.weathair.exceptions.TownshipException;
import com.weathair.repositories.AirIndicatorRepository;
import com.weathair.repositories.TownshipRepository;

/**
 * @author Nicolas : make the crud for Air Indicator
 *
 */
@Service
@Transactional
public class AirIndicatorService {

	public AirIndicatorRepository airIndicatorRepository;
	public TownshipRepository townshipRepository;

	public AirIndicatorService(AirIndicatorRepository airIndicatorRepository, TownshipRepository townshipRepository) {
		super();
		this.airIndicatorRepository = airIndicatorRepository;
		this.townshipRepository = townshipRepository;
	}

	/**
	 * @return return a list of all air indicators in base
	 */
	public List<AirIndicator> getAllAirIndicators() throws AirIndicatorException {
		List<AirIndicator> listAirIndicators = airIndicatorRepository.findAll();

		if (!listAirIndicators.isEmpty()) {
			return listAirIndicators;
		} else {
			throw new AirIndicatorException("There is no Air Indicator in this base.");
		}
	}
	
	/**
	 * @param townshipName
	 * @param limit
	 * @return
	 * @throws AirIndicatorException
	 */
	public List<AirIndicator> getAirIndicatorsByTownshipName(String townshipName) throws AirIndicatorException{
		List<AirIndicator> listAirIndicators = airIndicatorRepository.findByTownshipName(townshipName); 
		if (!listAirIndicators.isEmpty()) {
			return listAirIndicators;
		} else { 
			throw new AirIndicatorException("No Air Indicator with Township name " + townshipName + " has been found in DB");
		}
	}

	/**
	 * @param id
	 * @return return an air indicator from his id
	 */
	public AirIndicator getAirIndicatorById(Integer id) throws AirIndicatorException {
		Optional<AirIndicator> airIndicatorOptional = airIndicatorRepository.findById(id);
		if (airIndicatorOptional.isPresent()) {
			return airIndicatorOptional.get();
		} else {
			throw new AirIndicatorException("There is no air indicator with this id.");
		}
	}
	
	public AirIndicator getFromApiWaqi(String townshipName) throws JsonProcessingException, TownshipException {
        String token = "292826f99ed5cb9786dcb5b462297fa67201bca1";
        
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        
        HttpEntity<String> reponse = null;
        
        try {
            reponse = restTemplate.getForEntity("https://api.waqi.info/feed/" + townshipName + "/?token=" + token, String.class);
        } catch (Exception e) {
            System.err.println("Couldn't extract air indicators for township " + townshipName + " " + e.getMessage());
        }
        
        JsonNode root = objectMapper.readTree(reponse.getBody());
        JsonNode data = root.path("data");
        JsonNode iaqi = data.path("iaqi");
        
        AirIndicatorDto airIndicatorDto = new AirIndicatorDto();
        airIndicatorDto.setDateTime(LocalDateTime.now());
        airIndicatorDto.setTownshipName(townshipName);
        airIndicatorDto.setAqi(data.path("aqi").asInt());
        airIndicatorDto.setNo2(iaqi.path("no2").path("v").asDouble());
        airIndicatorDto.setO3(iaqi.path("o3").path("v").asDouble());
        airIndicatorDto.setPm10(iaqi.path("pm10").path("v").asDouble());
        
        AirIndicator airIndicator = new AirIndicator();
        dtoToEntity(airIndicator, airIndicatorDto);
        
        return airIndicator;
        
	}

	/**
	 * @param dto : Create an Air indicator and save it in base
	 * @return
	 * @throws TownshipException 
	 */
	public AirIndicator createAirIndicator(AirIndicatorDto airIndicatorDto) throws TownshipException {

		AirIndicator airIndicator = new AirIndicator();
		dtoToEntity(airIndicator, airIndicatorDto);
		return airIndicatorRepository.save(airIndicator);
	}

	//@Scheduled(initialDelay = 300 * 1000, fixedDelay = 3600 * 1000)
	public void insertAirIndicatorsFromApiWaqi() throws TownshipException, JsonProcessingException {
		System.out.println("Inserting AirIndicators into DB...");
		Long start = System.currentTimeMillis();
		List<Township> townships = getAllTownships();
		
		List<AirIndicator> airIndicators = new ArrayList<>();
		for (Township township : townships) {
			airIndicators.add(getFromApiWaqi(township.getName()));
		}
		
		Long stop = System.currentTimeMillis();
        String time = new SimpleDateFormat("mm:ss:SSS").format(new Date(stop - start));
		
		airIndicatorRepository.saveAll(airIndicators);
        System.out.println("AirIndicators database updated in " + time);
	}
	
	/**
	 * @param id			id
	 * @param 			airIndicatorDTO
	 * @return 			updated Air indicator in base
	 * @throws 			AirIndicatorException
	 * @throws TownshipException 
	 */
	public AirIndicator updateAirIndicator(Integer id, AirIndicatorDto airIndicatorDto) throws AirIndicatorException, TownshipException {
		AirIndicator airIndicatorUpdate = getAirIndicatorById(id);
		dtoToEntity(airIndicatorUpdate, airIndicatorDto);
		return airIndicatorRepository.save(airIndicatorUpdate);
	}

	/**
	 * @param airIndicator verify if an airindicator is in base by him id and then
	 *                     Delete this air indicator
	 */
	public void deleteAirIndicator(Integer id) throws AirIndicatorException {
		AirIndicator airIndicatorToDelete = getAirIndicatorById(id);
		airIndicatorRepository.delete(airIndicatorToDelete);
	}
	
	private List<Township> getAllTownships() throws TownshipException {
		List<Township> townships = townshipRepository.findInAirIndicator();
		if (!townships.isEmpty()) {
			return townships;
		} else {
			throw new TownshipException("There are no townships in DB");
		}
	}
	
	private Township getTownshipByName(String name) throws TownshipException {
		List<Township> townships = townshipRepository.findByNameOrderByPopulationDesc(name);
		if (!townships.isEmpty()) {
			return townships.get(0);
		} else {
			throw new TownshipException("No Township with name " + name + " has been found in DB");
		}
	}
	
	private AirIndicator dtoToEntity(AirIndicator airIndicator, AirIndicatorDto airIndicatorDto) throws TownshipException {
		airIndicator.setDateTime(airIndicatorDto.getDateTime());
		airIndicator.setTownship(this.getTownshipByName(airIndicatorDto.getTownshipName()));
		airIndicator.setAqi(airIndicatorDto.getAqi());
		airIndicator.setNo2(airIndicatorDto.getNo2());
		airIndicator.setO3(airIndicatorDto.getO3());
		airIndicator.setPm10(airIndicatorDto.getPm10());
		return airIndicator;
	}
}
