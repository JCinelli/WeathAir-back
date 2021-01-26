package com.weathair.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weathair.entities.indicators.MeteoIndicator;
import com.weathair.exceptions.MeteoIndicatorException;
import com.weathair.exceptions.UserException;
import com.weathair.services.MeteoIndicatorService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
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
}

