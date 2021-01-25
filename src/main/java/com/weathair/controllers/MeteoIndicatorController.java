package com.weathair.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weathair.dto.indicators.MeteoIndicatorDto;
import com.weathair.entities.indicators.AirIndicator;
import com.weathair.entities.indicators.MeteoIndicator;
import com.weathair.exceptions.AirIndicatorException;
import com.weathair.exceptions.MeteoIndicatorException;
import com.weathair.services.MeteoIndicatorService;

/**
 * @author tarbo controller of meteo indicator
 *
 */
@CrossOrigin
@RestController
@RequestMapping("meteoindicators")
public class MeteoIndicatorController {

	private MeteoIndicatorService meteoIndicatorService;

	public MeteoIndicatorController(MeteoIndicatorService meteoIndicatorService) {
		super();
		this.meteoIndicatorService = meteoIndicatorService;
	}

	/**
	 * @return all meteo indicators
	 */
	@GetMapping
	public List<MeteoIndicator> listAllMeteoIndicator() throws MeteoIndicatorException {
		return meteoIndicatorService.getAllMeteoIndicators();
	}
	
	@GetMapping("township={townshipName}/{limit}")
	public List<MeteoIndicator> listMeteoIndicatorsByTownshipName(@PathVariable String townshipName, @PathVariable int limit) throws MeteoIndicatorException{
		return meteoIndicatorService.getMeteoIndicatorsByTownshipName(townshipName, limit);
	}
	
//	@GetMapping("{townshipName}")
//	public ResponseEntity<?> meteoIndicatorByTownshipName(@PathVariable String townshipName) throws JsonMappingException, JsonProcessingException {
//		MeteoIndicatorDTO meteoIndicatorDto = this.meteoIndicatorService.findByTownshipName(townshipName);
//		return ResponseEntity.ok(meteoIndicatorDto);
//
//	}

	/**
	 * @param id
	 * @return a meteo indicator by id
	 * @throws MeteoIndicatorException
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> meteoIndicatorByUser(@PathVariable Integer id) throws MeteoIndicatorException {
		MeteoIndicator meteoIndicator = this.meteoIndicatorService.getMeteoIndicatorById(id);
		return ResponseEntity.ok(meteoIndicator);

	}

	/**
	 * @param dto
	 * @return a new meteo indicator
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@PostMapping
	public ResponseEntity<?> createNewMeteoIndicator(@RequestBody MeteoIndicatorDto meteoIndicatorDto) {
		return ResponseEntity.ok(meteoIndicatorService.createMeteoIndicator(meteoIndicatorDto));
	}

	/**
	 * @param id
	 * @param newTemperature
	 * @throws MeteoIndicatorException
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@PutMapping("{id}")
	public ResponseEntity<?> updateMeteoIndicator(@RequestParam Integer id, @RequestBody MeteoIndicatorDto meteoIndicatorDto) throws MeteoIndicatorException {
		meteoIndicatorService.updateMeteoIndicator(id, meteoIndicatorDto);
		return ResponseEntity.ok("The meteo indicator with id " + id + " has been successfully updated");
	}
	
		
	/**
	 * @param id
	 * @throws MeteoIndicatorException
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteMeteoIndicatorByUser(@RequestParam Integer id) throws MeteoIndicatorException {
		meteoIndicatorService.deleteMeteoIndicator(id);
		return ResponseEntity.ok("The meteo indicator with id " + id + " has been successfully deleted");

	}

}
