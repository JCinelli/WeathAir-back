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

import com.weathair.dto.indicators.MeteoIndicatorDTO;
import com.weathair.entities.indicators.MeteoIndicator;
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
	 * @return all meteo indicators with "all" on url
	 */
	@GetMapping
	public List<MeteoIndicator> listAllMeteoIndicator() throws MeteoIndicatorException {
		return meteoIndicatorService.getAllMeteoIndicators();
	}

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
	@PostMapping
	public ResponseEntity<?> createNewMeteoIndicator(@RequestBody MeteoIndicatorDTO dto) {
		return ResponseEntity.ok(meteoIndicatorService.createMeteoIndicator(dto));
	}

	/**
	 * @param id
	 * @param newTemperature
	 * @throws MeteoIndicatorException
	 */
	@PutMapping("{id}")
	public void updateMeteoIndicatorTemperature(@RequestParam Integer id, @RequestBody Integer newTemperature) throws MeteoIndicatorException {
		meteoIndicatorService.updateMeteoIndicatorTemperature(id, newTemperature);
	}
	
	/**
	 * @param id
	 * @param newWind
	 * @throws MeteoIndicatorException
	 */
	@PutMapping("{id}")
	public void updateMeteoIndicatorWind(@RequestParam Integer id, @RequestBody Integer newWind) throws MeteoIndicatorException {
		meteoIndicatorService.updateMeteoIndicatorWind(id, newWind);
	}
	
	/**
	 * @param id
	 * @param newDirWind
	 * @throws MeteoIndicatorException
	 */
	@PutMapping("{id}")
	public void updateMeteoIndicatorDirWind(@RequestParam Integer id, @RequestBody Integer newDirWind) throws MeteoIndicatorException {
		meteoIndicatorService.updateMeteoIndicatorDirWind(id, newDirWind);
	}
	
	/**
	 * @param id
	 * @param newProbaRain
	 * @throws MeteoIndicatorException
	 */
	@PutMapping("{id}")
	public void updateMeteoIndicatorProbaRain(@RequestParam Integer id, @RequestBody Integer newProbaRain) throws MeteoIndicatorException {
		meteoIndicatorService.updateMeteoIndicatorProbaRain(id, newProbaRain);
	}
	
	/**
	 * @param id
	 * @param newProbaFog
	 * @throws MeteoIndicatorException
	 */
	@PutMapping("{id}")
	public void updateMeteoIndicatorProbaFog(@RequestParam Integer id, @RequestBody Integer newProbaFog) throws MeteoIndicatorException {
		meteoIndicatorService.updateMeteoIndicatorProbaFog(id, newProbaFog);
	}
	
	/**
	 * @param id
	 * @throws MeteoIndicatorException
	 */
	@DeleteMapping("{id}")
	public void deleteMeteoIndicatorByUser(@RequestParam Integer id) throws MeteoIndicatorException {
		meteoIndicatorService.deleteMeteoIndicator(id);
	}

}
