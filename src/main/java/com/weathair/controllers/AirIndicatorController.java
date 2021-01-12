package com.weathair.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

import com.weathair.dto.indicators.AirIndicatorDto;
import com.weathair.entities.indicators.AirIndicator;
import com.weathair.exceptions.AirIndicatorException;
import com.weathair.exceptions.TownshipException;
import com.weathair.services.AirIndicatorService;

/**
 * @author tarbo
 * controller of air indicator
 */
@CrossOrigin
@RestController
@RequestMapping("airindicators")
public class AirIndicatorController {

	private AirIndicatorService airIndicatorService;

	public AirIndicatorController(AirIndicatorService airIndicatorService) {
		super();
		this.airIndicatorService = airIndicatorService;
	}

	/**
	 * @return all air indicators with "all" on url
	 * @throws AirIndicatorException
	 */
	@GetMapping
	public List<AirIndicator> listAllAirIndicator() throws AirIndicatorException {
		return airIndicatorService.getAllAirIndicators();
	}

	/**
	 * @param id
	 * @return an air indicator by id
	 * @throws AirIndicatorException
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> airIndicatorByUser(@PathVariable Integer id) throws AirIndicatorException {
		AirIndicator airIndicator = this.airIndicatorService.getAirIndicatorById(id);

		return ResponseEntity.ok(airIndicator);

	}

	/**
	 * @param dto
	 * @return a new air indicator
	 * @throws AirIndicatorException
	 * @throws TownshipException 
	 */
	@PostMapping
	public ResponseEntity<?> createNewAirIndicator(@RequestBody AirIndicatorDto airIndicatorDto) throws AirIndicatorException, TownshipException {
		return ResponseEntity.ok(airIndicatorService.createAirIndicator(airIndicatorDto));
	}

	/**
	 * @param id
	 * @param newDateTime
	 * @throws AirIndicatorException
	 * @throws TownshipException 
	 */
	@PutMapping("{id}")
	public ResponseEntity<?> updateAirIndicator(@RequestParam Integer id, @RequestBody AirIndicatorDto airIndicatorDto)
			throws AirIndicatorException, TownshipException {
		airIndicatorService.updateAirIndicator(id, airIndicatorDto);
		return ResponseEntity.ok("The air indicator with id " + id + " has been successfully updated");
	}

	/**
	 * @param id
	 * delete an air indicator by id
	 * @throws AirIndicatorException
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteAirIndicatorByUser(@RequestParam Integer id) throws AirIndicatorException {
		airIndicatorService.deleteAirIndicator(id);
		return ResponseEntity.ok("The air indicator with id " + id + " has been successfully deleted");
	}
}
