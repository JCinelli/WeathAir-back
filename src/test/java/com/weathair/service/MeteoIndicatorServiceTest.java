package com.weathair.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.boot.test.context.SpringBootTest;
import com.weathair.dto.indicators.MeteoIndicatorDto;
import com.weathair.entities.indicators.MeteoIndicator;
import com.weathair.exceptions.MeteoIndicatorException;
import com.weathair.exceptions.UserException;
import com.weathair.services.MeteoIndicatorService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MeteoIndicatorServiceTest {

	@Autowired
	private MeteoIndicatorService meteoIndicatorService;
	
	@Test
	public void testFindAllMeteosIndicators() throws UserException, MeteoIndicatorException {
		List<MeteoIndicator> meteoIndicators = meteoIndicatorService.getAllMeteoIndicators();
		assertThat(!meteoIndicators.isEmpty());
	}
	
	@Test
	public void testFindMeteoIndicatorById() throws UserException, MeteoIndicatorException {
		MeteoIndicator meteoIndicators = meteoIndicatorService.getMeteoIndicatorById(34996);
		assertThat(meteoIndicators.getDescription()).isEqualTo("mist");
	}
	
	@Test
	public void testFindMeteoIndicatorByTownshipname() throws UserException, MeteoIndicatorException {
//		MeteoIndicator meteoIndicators = (MeteoIndicator) meteoIndicatorService.getMeteoIndicatorsByTownshipName("Montpellier");
	//	assertThat(meteoIndicators.getDescription()).isEqualTo("mist");
	}
	
	@Test
	public void testCreateMeteoIndicator() throws UserException, MeteoIndicatorException {
		MeteoIndicatorDto meteoIndicatorsNew = new MeteoIndicatorDto();
		meteoIndicatorsNew.setDescription("new meteo indicator");
		meteoIndicatorService.createMeteoIndicator(meteoIndicatorsNew);
	}
}

