package com.weathair.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weathair.entities.GpsCoordinate;
import com.weathair.exceptions.AirIndicatorException;
import com.weathair.exceptions.TownshipException;
import com.weathair.services.GpsCoordinateService;

@CrossOrigin
@RestController
@RequestMapping("gpscoordinates")
public class GpsCoordinateController {
	
	private GpsCoordinateService gpsCoordinateService;
	
	public GpsCoordinateController(GpsCoordinateService gpsCoordinateService) {
		this.gpsCoordinateService = gpsCoordinateService;
	}
	
	/**
	 * @return all air indicators with "all" on url
	 * @throws AirIndicatorException
	 */
	@GetMapping
	public List<GpsCoordinate> listAllGpsCoordinates() throws TownshipException {
		return gpsCoordinateService.getAllGpsCoordinates();
	}
	
	@GetMapping("township={townshipName}")
	public List<GpsCoordinate> listGpsCoordinatesByTownshipName(@PathVariable String townshipName) throws TownshipException {
		return gpsCoordinateService.getGpsCoordinatesByTownshipName(townshipName);
	}

}
