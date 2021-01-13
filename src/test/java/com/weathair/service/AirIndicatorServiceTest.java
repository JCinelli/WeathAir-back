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

import com.weathair.dto.indicators.AirIndicatorDto;
import com.weathair.entities.indicators.AirIndicator;
import com.weathair.exceptions.AirIndicatorException;
import com.weathair.exceptions.TownshipException;
import com.weathair.exceptions.UserException;
import com.weathair.services.AirIndicatorService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AirIndicatorServiceTest {

	@Autowired
	private AirIndicatorService airIndicatorService;
	
	@Test
	public void testFindAllAirsIndicatos() throws UserException, AirIndicatorException {
		List<AirIndicator> airIndicators = airIndicatorService.getAllAirIndicators();
		assertThat(!airIndicators.isEmpty());
	}
	
	@Test
	public void testFindAirIndicatorById() throws UserException, AirIndicatorException {
		AirIndicator airIndicators = airIndicatorService.getAirIndicatorById(1);
		
		assertThat(airIndicators.getNo2()).isEqualTo(10.0);
	}
	
//	@Test 
//	public void testCreateAirIndicator() throws UserException, AirIndicatorException, TownshipException {
//		AirIndicatorDto airIndicators = new AirIndicatorDto();
//	
//		airIndicators.setNo2(20.0);
//		airIndicators.setO3(20.20);
//		airIndicators.setNameTownship("Aast");
//		airIndicatorService.createAirIndicator(airIndicators);
//		
//		AirIndicator airIndicatorTest = airIndicatorService.getAirIndicatorById(2);
//		assertThat(airIndicators).isEqualTo(airIndicatorTest);
//	}
}
