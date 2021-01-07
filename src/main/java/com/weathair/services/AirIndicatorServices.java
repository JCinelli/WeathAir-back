package com.weathair.services;

import java.util.List;

import com.weathair.dto.AirIndicatorDTO;
import com.weathair.entities.indicators.AirIndicator;
import com.weathair.repositories.AirIndicatorRepository;

/**
 * @author Nicolas : Création de l'air indicator service, avec les fonctions du crud : 
 *  create, read (get), update and delete.
 *
 */
public class AirIndicatorServices {

	public AirIndicatorRepository airIndicatorRepo;

	
	public AirIndicatorServices(AirIndicatorRepository airIndicatorRepo) {
		super();
		this.airIndicatorRepo  = airIndicatorRepo;
	}
	
	public AirIndicator createAirIndicator(AirIndicatorDTO dto) {

		AirIndicator airIndicator = new AirIndicator();
		airIndicator.setDateTime(dto.getDateTime());
		airIndicator.setNameStation(dto.getNameStation());
		airIndicator.setCodeStation(dto.getCodeStation());
		airIndicator.setLabel(dto.getLabel());
		airIndicator.setValue(dto.getValue());

		return this.airIndicatorRepo.save(airIndicator);
	}

	public List<AirIndicator> getAllAbsences() {
		return this.airIndicatorRepo.findAll();
	}
	
}
