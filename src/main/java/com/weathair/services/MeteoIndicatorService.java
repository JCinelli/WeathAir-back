package com.weathair.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weathair.dto.indicators.MeteoIndicatorDto;
import com.weathair.entities.Township;
import com.weathair.entities.indicators.AirIndicator;
import com.weathair.entities.indicators.MeteoIndicator;
import com.weathair.exceptions.AirIndicatorException;
import com.weathair.exceptions.MeteoIndicatorException;
import com.weathair.repositories.MeteoIndicatorRepository;
import com.weathair.repositories.TownshipRepository;

/**
 * @author Nicolas : make the crud for Meteo Indicator
 *
 */
@Service
@Transactional
public class MeteoIndicatorService {

	private MeteoIndicatorRepository meteoIndicatorRepository;
	
	private TownshipRepository townshipRepository;

	public MeteoIndicatorService(MeteoIndicatorRepository meteoIndicatorRepository, TownshipRepository townshipRepository) {
		super();
		this.meteoIndicatorRepository = meteoIndicatorRepository;
		this.townshipRepository = townshipRepository;
	}

	/**
	 * @return a list of all meteo indicators in base
	 */
	public List<MeteoIndicator> getAllMeteoIndicators() throws MeteoIndicatorException {
		List<MeteoIndicator> listMeteoIndicator = meteoIndicatorRepository.findAll();

		if (!listMeteoIndicator.isEmpty()) {
			return listMeteoIndicator;
		} else {
			throw new MeteoIndicatorException("There is no meteo Indicator in this base.");
		}
	}

	/**
	 * @param id
	 * @return a meteo indicator from his id
	 */
	public MeteoIndicator getMeteoIndicatorById(Integer id) throws MeteoIndicatorException {
		Optional<MeteoIndicator> meteoIndicatorOptional = meteoIndicatorRepository.findById(id);
		if (meteoIndicatorOptional.isPresent()) {
			return meteoIndicatorOptional.get();
		} else {
			throw new MeteoIndicatorException("There is no meteo indicator with this id.");
		}
	}
	
	/**
	 * @param townshipName
	 * @param limit
	 * @return
	 * @throws MeteoIndicatorException
	 */
	public List<MeteoIndicator> getMeteoIndicatorsByTownshipName(String townshipName) throws MeteoIndicatorException{
		List<MeteoIndicator> listMeteoIndicators = meteoIndicatorRepository.findByTownshipName(townshipName); 
		if (!listMeteoIndicators.isEmpty()) {
			return listMeteoIndicators;
		} else { 
			throw new MeteoIndicatorException("No Meteo Indicator with Township name " + townshipName + " has been found in DB");
		}
	}

	/**
	 * @param dto : Create an Meteo indicator and save it in base
	 * @return a meteo Indicator insert in data base
	 */
	public MeteoIndicator createMeteoIndicator(MeteoIndicatorDto dto) {
		
		MeteoIndicator meteoIndicator = this.dtoToEntity(dto);

		return this.meteoIndicatorRepository.save(meteoIndicator);
	}
	
	/**
	 * Create meteo indicators from dto and save them all in database
	 * 
	 * 
	 * @param dtos 			List of DTOs
	 * @return
	 */
	public List<MeteoIndicator> saveAllMeteoIndicators(List<MeteoIndicatorDto> dtos) {
		
		List<MeteoIndicator> meteoIndicators = new ArrayList<>();
		
		for (MeteoIndicatorDto dto : dtos) {
			meteoIndicators.add(this.dtoToEntity(dto));
		}
		return this.meteoIndicatorRepository.saveAll(meteoIndicators);
	}

	/**
	 * @param id
	 * @param newcodeStation
	 * @return New Temperature indicator in base
	 * @throws MeteoIndicatorException
	 */
	public MeteoIndicator updateMeteoIndicator(Integer id, MeteoIndicatorDto dto)
			throws MeteoIndicatorException {
		MeteoIndicator meteoIndicatorUpdate = getMeteoIndicatorById(id);
		meteoIndicatorUpdate.setDateTime(dto.getDateTime());
		meteoIndicatorUpdate.setDescription(dto.getDescription());
		meteoIndicatorUpdate.setTemperature(dto.getTemperature());
		meteoIndicatorUpdate.setFeelsLike(dto.getFeelsLike());
		meteoIndicatorUpdate.setWindSpeed(dto.getWindSpeed());
		meteoIndicatorUpdate.setWindDeg(dto.getWindDeg());
		meteoIndicatorUpdate.setHumidity(dto.getHumidity());
		meteoIndicatorUpdate.setTownship(townshipRepository.findByNameContainingOrderByPopulationDesc(dto.getTownshipName()).get(0));

		return meteoIndicatorRepository.save(meteoIndicatorUpdate);
	}


	/**
	 * @param id , verify if a meteo indicator is in base by him id and then Delete
	 *           this meteo indicator
	 */
	public void deleteMeteoIndicator(Integer id) throws MeteoIndicatorException {
		MeteoIndicator meteoIndicatorToDelete = getMeteoIndicatorById(id);

		meteoIndicatorRepository.delete(meteoIndicatorToDelete);

	}
	
	/**
	 * Save all the meteo indicators every hours for Occitanie
	 * 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	//@Scheduled(initialDelay = 300 * 1000, fixedDelay = 3600 * 1000)
	public void saveUpdateIndicatorsForOccitanie() throws JsonMappingException, JsonProcessingException {
		List<Township> townships = townshipRepository.findInAirIndicator();
		List<MeteoIndicatorDto> indicators = new ArrayList<>();
		for (Township township : townships) {
			try {
				
				MeteoIndicatorDto meteoIndicatorDto = this.findByTownshipName(township.getName());
				meteoIndicatorDto.setTownshipName(township.getName());
				indicators.add(meteoIndicatorDto);
				
			} catch (MeteoIndicatorException e) {
				System.err.println(e + township.getName());
				continue;
			}
		}
		
		this.saveAllMeteoIndicators(indicators);
	}
	
	/**
	 * 
	 * This method return the actual meteo datas of the township with his name
	 * 
	 * @param townshipName					name of the township
	 * @return a MeteoIndicatorDto
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws MeteoIndicatorException 
	 */
	private MeteoIndicatorDto findByTownshipName(String townshipName) throws JsonMappingException, JsonProcessingException, MeteoIndicatorException {
		
		String url = "https://api.openweathermap.org/data/2.5/weather?appid=32fa512dfbcfffec2a96f222e2a0e6dd&units=metric&q=";
		
		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		
		HttpEntity<String> response = null;
		
		try {
			 response = rt.getForEntity(url + townshipName, String.class);
		} catch (Exception e) {
			System.err.println(e);
		}
			
			if (response != null) {
				JsonNode root = mapper.readTree(response.getBody());
				JsonNode weather = root.path("weather");
				JsonNode main = root.path("main");
				JsonNode wind = root.path("wind");

				MeteoIndicatorDto meteoIndicatorDto = new MeteoIndicatorDto();
				meteoIndicatorDto.setDateTime(LocalDateTime.now());
				meteoIndicatorDto.setDescription(weather.findPath("description").asText());
				meteoIndicatorDto.setTemperature(main.path("temp").asDouble());
				meteoIndicatorDto.setFeelsLike(main.path("feels_like").asDouble());
				meteoIndicatorDto.setWindSpeed(wind.path("speed").asDouble());
				meteoIndicatorDto.setWindDeg(wind.path("deg").asInt());
				meteoIndicatorDto.setHumidity(main.path("humidity").asInt());
				meteoIndicatorDto.setTownshipName(root.path("name").asText());

				return meteoIndicatorDto;
			} else {
				throw new MeteoIndicatorException("Township '" + townshipName + "' not found in OpenWeatherMap !");
			}
	}
	
	private MeteoIndicator dtoToEntity(MeteoIndicatorDto dto) {
		MeteoIndicator meteoIndicatorUpdate = new MeteoIndicator();
		meteoIndicatorUpdate.setDateTime(dto.getDateTime());
		meteoIndicatorUpdate.setDescription(dto.getDescription());
		meteoIndicatorUpdate.setTemperature(dto.getTemperature());
		meteoIndicatorUpdate.setFeelsLike(dto.getFeelsLike());
		meteoIndicatorUpdate.setWindSpeed(dto.getWindSpeed());
		meteoIndicatorUpdate.setWindDeg(dto.getWindDeg());
		meteoIndicatorUpdate.setHumidity(dto.getHumidity());
		meteoIndicatorUpdate.setTownship(townshipRepository.findByNameContainingOrderByPopulationDesc(dto.getTownshipName()).get(0));
		
		return meteoIndicatorUpdate;
	}
}
