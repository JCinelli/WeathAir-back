package com.weathair.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.indicators.AirIndicatorDTO;
import com.weathair.entities.indicators.AirIndicator;
import com.weathair.exceptions.AirIndicatorException;
import com.weathair.repositories.AirIndicatorRepository;

/**
 * @author Nicolas : make the crud for Air Indicator
 *
 */
@Service
public class AirIndicatorService {

	public AirIndicatorRepository airIndicatorRepository;

	public AirIndicatorService(AirIndicatorRepository airIndicatorRepository) {
		super();
		this.airIndicatorRepository = airIndicatorRepository;
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

	/**
	 * @param dto : Create an Air indicator and save it in base
	 * @return
	 */
	public AirIndicator createAirIndicator(AirIndicatorDTO dto) {

		AirIndicator airIndicator = new AirIndicator();
		airIndicator.setDateTime(dto.getDateTime());
		airIndicator.setNameStation(dto.getNameStation());
		airIndicator.setCodeStation(dto.getCodeStation());
		airIndicator.setLabel(dto.getLabel());
		airIndicator.setValue(dto.getValue());

		return this.airIndicatorRepository.save(airIndicator);
	}

	/**
	 * @param id			id
	 * @param 			airIndicatorDTO
	 * @return 			updated Air indicator in base
	 * @throws 			AirIndicatorException
	 */
	public AirIndicator updateAirIndicator(Integer id, AirIndicatorDTO airIndicatorDTO) throws AirIndicatorException {
		AirIndicator airIndicatorUpdate = getAirIndicatorById(id);
		airIndicatorUpdate.setCodeStation(airIndicatorDTO.getCodeStation());
		airIndicatorUpdate.setNameStation(airIndicatorDTO.getNameStation());
		airIndicatorUpdate.setLabel(airIndicatorDTO.getLabel());
		airIndicatorUpdate.setValue(airIndicatorDTO.getValue());
		airIndicatorUpdate.setDateTime(airIndicatorDTO.getDateTime());

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
}
