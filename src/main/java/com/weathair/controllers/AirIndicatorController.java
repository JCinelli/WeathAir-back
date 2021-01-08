package com.weathair.controllers;

import java.time.LocalDateTime;
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

import com.weathair.dto.indicators.AirIndicatorDTO;
import com.weathair.entities.indicators.AirIndicator;
import com.weathair.exceptions.AirIndicatorException;
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
	 */
	@PostMapping
	public ResponseEntity<?> createNewAirIndicator(@RequestBody AirIndicatorDTO dto) throws AirIndicatorException {
		return ResponseEntity.ok(airIndicatorService.createAirIndicator(dto));
	}

	/**
	 * @param id
	 * @param newDateTime
	 * @throws AirIndicatorException
	 */
	@PutMapping("{id}")
	public void updateAirIndicatorDateTime(@RequestParam Integer id, @RequestBody LocalDateTime newDateTime)
			throws AirIndicatorException {
		airIndicatorService.updateAirIndicatorDateTime(id, newDateTime);
	}

	/**
	 * @param id
	 * @param newNameStation
	 * @throws AirIndicatorException
	 */
	@PutMapping("{id}")
	public void updateAirIndicatorNameStation(@RequestParam Integer id, @RequestBody String newNameStation)
			throws AirIndicatorException {
		airIndicatorService.updateAirIndicatorNameStation(id, newNameStation);
	}

	/**
	 * @param id
	 * @param newCodeStation
	 * @throws AirIndicatorException
	 */
	@PutMapping("{id}")
	public void updateAirIndicatorCodeStation(@RequestParam Integer id, @RequestBody String newCodeStation)
			throws AirIndicatorException {
		airIndicatorService.updateAirIndicatorCodeStation(id, newCodeStation);
	}

	/**
	 * @param id
	 * @param newLabel
	 * @throws AirIndicatorException
	 */
	@PutMapping("{id}")
	public void updateAirIndicatorLabel(@RequestParam Integer id, @RequestBody String newLabel)
			throws AirIndicatorException {
		airIndicatorService.updateAirIndicatorLabel(id, newLabel);
	}

	/**
	 * @param id
	 * @param newValue
	 * @throws AirIndicatorException
	 */
	@PutMapping("{id}")
	public void updateAirIndicatorValue(@RequestParam Integer id, @RequestBody Double newValue)
			throws AirIndicatorException {
		airIndicatorService.updateAirIndicatorValue(id, newValue);
	}

	/**
	 * @param id
	 * delete an air indicator by id
	 * @throws AirIndicatorException
	 */
	@DeleteMapping("{id}")
	public void deleteAirIndicatorByUser(@RequestParam Integer id) throws AirIndicatorException {
		airIndicatorService.deleteAirIndicator(id);
	}
}
