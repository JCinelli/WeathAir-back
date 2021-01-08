package com.weathair.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.indicators.MeteoIndicatorDTO;
import com.weathair.entities.indicators.MeteoIndicator;
import com.weathair.exceptions.MeteoIndicatorException;
import com.weathair.repositories.MeteoIndicatorRepository;

/**
 * @author Nicolas : make the crud for Meteo Indicator
 *
 */
@Service
public class MeteoIndicatorService {

	public MeteoIndicatorRepository meteoIndicatorRepository;

	public MeteoIndicatorService(MeteoIndicatorRepository meteoIndicatorRepository) {
		super();
		this.meteoIndicatorRepository = meteoIndicatorRepository;
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
	 * @param dto : Create an Meteo indicator and save it in base
	 * @return a meteo Indicator insert in data base
	 */
	public MeteoIndicator createMeteoIndicator(MeteoIndicatorDTO dto) {

		MeteoIndicator meteoIndicator = new MeteoIndicator();
		meteoIndicator.setDateTime(dto.getDateTime());
		meteoIndicator.setDirWind(dto.getDirWind());
		meteoIndicator.setProbaFog(dto.getProbaFog());
		meteoIndicator.setProbaRain(dto.getProbaRain());
		meteoIndicator.setTemperature(dto.getTemperature());
		meteoIndicator.setWind(dto.getWind());

		return this.meteoIndicatorRepository.save(meteoIndicator);
	}

	/**
	 * @param id
	 * @param newcodeStation
	 * @return New Temperature indicator in base
	 * @throws MeteoIndicatorException
	 */
	public MeteoIndicator updateMeteoIndicatorTemperature(Integer id, Integer newTemperature)
			throws MeteoIndicatorException {
		MeteoIndicator meteoIndicatorUpdate = getMeteoIndicatorById(id);
		meteoIndicatorUpdate.setTemperature(newTemperature);
		return meteoIndicatorRepository.save(meteoIndicatorUpdate);
	}

	/**
	 * @param id
	 * @param newcodeStation
	 * @return New datetime indicator in base
	 * @throws MeteoIndicatorException
	 */
	public MeteoIndicator updateMeteoIndicatorDateTime(Integer id, LocalDateTime newDateTime)
			throws MeteoIndicatorException {
		MeteoIndicator meteoIndicatorUpdate = getMeteoIndicatorById(id);
		meteoIndicatorUpdate.setDateTime(newDateTime);
		return meteoIndicatorRepository.save(meteoIndicatorUpdate);
	}

	/**
	 * @param id
	 * @param newcodeStation
	 * @return New wind indicator in base
	 * @throws MeteoIndicatorException
	 */
	public MeteoIndicator updateMeteoIndicatorWind(Integer id, Integer newWind) throws MeteoIndicatorException {
		MeteoIndicator meteoIndicatorUpdate = getMeteoIndicatorById(id);
		meteoIndicatorUpdate.setWind(newWind);
		return meteoIndicatorRepository.save(meteoIndicatorUpdate);
	}

	/**
	 * @param id
	 * @param newcodeStation
	 * @return New direction wind indicator in base
	 * @throws MeteoIndicatorException
	 */
	public MeteoIndicator updateMeteoIndicatorDirWind(Integer id, Integer newDirWind) throws MeteoIndicatorException {
		MeteoIndicator meteoIndicatorUpdate = getMeteoIndicatorById(id);
		meteoIndicatorUpdate.setDirWind(newDirWind);
		return meteoIndicatorRepository.save(meteoIndicatorUpdate);
	}

	/**
	 * @param id
	 * @param newcodeStation
	 * @return New Proba rain indicator in base
	 * @throws MeteoIndicatorException
	 */
	public MeteoIndicator updateMeteoIndicatorProbaRain(Integer id, Integer newProbaRain)
			throws MeteoIndicatorException {
		MeteoIndicator meteoIndicatorUpdate = getMeteoIndicatorById(id);
		meteoIndicatorUpdate.setProbaRain(newProbaRain);
		return meteoIndicatorRepository.save(meteoIndicatorUpdate);
	}

	/**
	 * @param id
	 * @param newcodeStation
	 * @return New proba Fog indicator in base
	 * @throws MeteoIndicatorException
	 */
	public MeteoIndicator updateMeteoIndicatorProbaFog(Integer id, Integer newProbaFog) throws MeteoIndicatorException {
		MeteoIndicator meteoIndicatorUpdate = getMeteoIndicatorById(id);
		meteoIndicatorUpdate.setProbaFog(newProbaFog);
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
}
