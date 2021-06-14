package com.weathair.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weathair.services.TownshipService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.weathair.dto.indicators.AirIndicatorDto;
import com.weathair.entities.indicators.AirIndicator;
import com.weathair.exceptions.AirIndicatorException;
import com.weathair.exceptions.TownshipException;
import com.weathair.services.AirIndicatorService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AirIndicatorServiceTest {

	@Autowired
	private AirIndicatorService airIndicatorService;

	@Test
	@Order(1)
	public void findAllAirIndicatorsTest() throws AirIndicatorException {
		List<AirIndicator> airIndicatorsList = airIndicatorService.getAllAirIndicators();
		assertThat(!airIndicatorsList.isEmpty());
	}

	@Test
	@Order(2)
	public void getAirIndicatorsByTownshipNameTest() throws AirIndicatorException {
		List<AirIndicator> airIndicatorsList = airIndicatorService.getAirIndicatorsByTownshipName("Montpellier");
		assertThat(!airIndicatorsList.isEmpty());
	}

	@Test
	@Order(3)
	public void getAirIndicatorByIdTest() throws AirIndicatorException {
		AirIndicator airIndicator = airIndicatorService.getAirIndicatorById(10505);
		assertThat(airIndicator.getAqi() == 42);
	}

	@Test
	@Order(4)
	public void getFromApiWaqiTest() throws JsonProcessingException, TownshipException {
		AirIndicator airIndicator = airIndicatorService.getFromApiWaqi("Montpellier");
		assertThat(airIndicator.getTownship().getName()).isEqualTo("Montpellier");
	}

	@Test
	@Order(5)
	public void createAirIndicatorTest() throws TownshipException, AirIndicatorException {
		int initIndicNb = airIndicatorService.getAllAirIndicators().size();
		AirIndicatorDto airIndicatorDto = new AirIndicatorDto();
		LocalDateTime now = LocalDateTime.now();
		airIndicatorDto.setDateTime(now);
		airIndicatorDto.setTownshipName("Montpellier");
		airIndicatorDto.setAqi(1990);
		airIndicatorService.createAirIndicator(airIndicatorDto);
		int finalIndicNb = airIndicatorService.getAllAirIndicators().size();

		assertThat(finalIndicNb).isEqualTo(initIndicNb + 1);

	}

	@Test
	@Order(6)
	public void updateAirIndicatorTest() throws AirIndicatorException, TownshipException {
		List<AirIndicator> airIndicatorMontpellierList = airIndicatorService.getAirIndicatorsByTownshipName("Montpellier");
		AirIndicatorDto airIndicatorDto = new AirIndicatorDto();
		LocalDateTime now = LocalDateTime.now();
		airIndicatorDto.setDateTime(now);
		airIndicatorDto.setTownshipName("Montpellier");
		airIndicatorDto.setAqi(1990);
		airIndicatorDto.setNo2(1000000000d);
		int id = 0;
		for (AirIndicator a : airIndicatorMontpellierList) {
			if (a.getAqi() == 1990) {
				id = a.getId();
				airIndicatorService.updateAirIndicator(id, airIndicatorDto);
			}
		}
		AirIndicator airIndicator = airIndicatorService.getAirIndicatorById(id);
		assertThat(airIndicator.getNo2()).isEqualTo(1000000000d);
	}

	@Test
	@Order(7)
	public void deleteAirIndicatorTest() throws AirIndicatorException {
		int initIndicNb = airIndicatorService.getAllAirIndicators().size();
		List<AirIndicator> airIndicatorMontpellierList = airIndicatorService.getAirIndicatorsByTownshipName("Montpellier");
		for (AirIndicator a : airIndicatorMontpellierList) {
			if (a.getAqi() == 1990) {
				airIndicatorService.deleteAirIndicator(a.getId());
			}
		}
		int finalIndicNb = airIndicatorService.getAllAirIndicators().size();

		assertThat(initIndicNb).isGreaterThan(finalIndicNb);
	}

//	@Test
//	@Order(8)
//	public void insertAirIndicatorsFromApiWaqiTest() throws AirIndicatorException, TownshipException, JsonProcessingException {
//		int initIndicNb = airIndicatorService.getAllAirIndicators().size();
//		airIndicatorService.insertAirIndicatorsFromApiWaqi();
//		int finIndicNb = airIndicatorService.getAllAirIndicators().size();
//
//		assertThat(finIndicNb).isGreaterThan(initIndicNb);
//	}
}
