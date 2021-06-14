package com.weathair.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.weathair.dto.indicators.MeteoIndicatorDto;
import com.weathair.entities.indicators.MeteoIndicator;
import com.weathair.exceptions.MeteoIndicatorException;
import com.weathair.services.MeteoIndicatorService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeteoIndicatorServiceTest {

	@Autowired
	private MeteoIndicatorService meteoIndicatorService;
	
	@Test
	@Order(1)
	public void findAllMeteosIndicatorsTest() throws MeteoIndicatorException {
		List<MeteoIndicator> meteoIndicators = meteoIndicatorService.getAllMeteoIndicators();
		assertThat(!meteoIndicators.isEmpty());
	}
	
	@Test
	@Order(2)
	public void findMeteoIndicatorByIdTest() throws MeteoIndicatorException {
		MeteoIndicator meteoIndicators = meteoIndicatorService.getMeteoIndicatorById(10055);
		assertThat(meteoIndicators.getDescription()).isEqualTo("clear sky");
	}
	
	@Test
	@Order(3)
	public void findMeteoIndicatorByTownshipnameTest() throws MeteoIndicatorException {
		List<MeteoIndicator> meteoIndicators = meteoIndicatorService.getMeteoIndicatorsByTownshipName("Montpellier");
		assertThat(!meteoIndicators.isEmpty());
	}
	
	@Test
	@Order(4)
	public void createMeteoIndicatorTest() {
		LocalDateTime now = LocalDateTime.now();
		MeteoIndicatorDto meteoIndicatorDto = new MeteoIndicatorDto();
		meteoIndicatorDto.setDescription("new meteo indicator");
		meteoIndicatorDto.setDateTime(now);
		meteoIndicatorDto.setTemperature(10000d);
		meteoIndicatorDto.setHumidity(10000);
		meteoIndicatorDto.setFeelsLike(10000d);
		meteoIndicatorDto.setWindDeg(10000);
		meteoIndicatorDto.setTownshipName("Montpellier");
		MeteoIndicator createdMeteoIndicator = meteoIndicatorService.createMeteoIndicator(meteoIndicatorDto);

		assertThat(createdMeteoIndicator.getDescription()).isEqualTo("new meteo indicator");
		assertThat(createdMeteoIndicator.getTemperature()).isEqualTo(10000d);
		assertThat(createdMeteoIndicator.getHumidity()).isEqualTo(10000);
		assertThat(createdMeteoIndicator.getFeelsLike()).isEqualTo(10000d);
		assertThat(createdMeteoIndicator.getWindDeg()).isEqualTo(10000);
		assertThat(createdMeteoIndicator.getTownship().getName()).isEqualTo("Montpellier");
	}

	@Test
	@Order(5)
	public void updateMeteoIndicatorTest() throws MeteoIndicatorException {
		LocalDateTime now = LocalDateTime.now();
		MeteoIndicatorDto meteoIndicatorDto = new MeteoIndicatorDto();
		meteoIndicatorDto.setDescription("new meteo indicator updated");
		meteoIndicatorDto.setDateTime(now);
		meteoIndicatorDto.setTemperature(20000d);
		meteoIndicatorDto.setHumidity(20000);
		meteoIndicatorDto.setFeelsLike(20000d);
		meteoIndicatorDto.setWindDeg(20000);
		meteoIndicatorDto.setTownshipName("Rodez");

		int id = 0;
		List<MeteoIndicator> meteoIndicators = meteoIndicatorService.getAllMeteoIndicators();
		for (MeteoIndicator m : meteoIndicators) {
			if (m.getDescription().equals("new meteo indicator")) {
				id = m.getId();
				meteoIndicatorService.updateMeteoIndicator(id, meteoIndicatorDto);
			}
		}

		MeteoIndicator updatedMeteoIndicator = meteoIndicatorService.getMeteoIndicatorById(id);

		assertThat(updatedMeteoIndicator.getDescription()).isEqualTo("new meteo indicator updated");
		assertThat(updatedMeteoIndicator.getTemperature()).isEqualTo(20000d);
		assertThat(updatedMeteoIndicator.getHumidity()).isEqualTo(20000);
		assertThat(updatedMeteoIndicator.getFeelsLike()).isEqualTo(20000d);
		assertThat(updatedMeteoIndicator.getWindDeg()).isEqualTo(20000);
		assertThat(updatedMeteoIndicator.getTownship().getName()).isEqualTo("Rodez");
	}

	@Test
	@Order(6)
	public void deleteMeteoIndicatorTest() throws MeteoIndicatorException {
		List<MeteoIndicator> meteoIndicators = meteoIndicatorService.getAllMeteoIndicators();
		int initialIndicatorsNb = meteoIndicators.size();
		for (MeteoIndicator m : meteoIndicators) {
			if (m.getDescription().contains("new meteo indicator")) {
				meteoIndicatorService.deleteMeteoIndicator(m.getId());
			}
		}
		int finalIndicatorsNb = meteoIndicatorService.getAllMeteoIndicators().size();

		assertThat(initialIndicatorsNb).isGreaterThan(finalIndicatorsNb);
	}

	@Test
	@Order(7)
	public void saveUpdateIndicatorsForOccitanieTest() throws MeteoIndicatorException, JsonProcessingException {
		int initialIndicatorsNb = meteoIndicatorService.getAllMeteoIndicators().size();
		meteoIndicatorService.saveUpdateIndicatorsForOccitanie();
		int finalIndicatorsNb = meteoIndicatorService.getAllMeteoIndicators().size();
		assertThat(finalIndicatorsNb).isGreaterThan(initialIndicatorsNb);

	}

}

