package com.weathair;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weathair.controllers.MeteoIndicatorController;
import com.weathair.controllers.TownshipController;
import com.weathair.dto.indicators.MeteoIndicatorDTO;
import com.weathair.entities.indicators.MeteoIndicator;
import com.weathair.repositories.TownshipRepository;
import com.weathair.services.MeteoIndicatorService;

@SpringBootApplication
@ComponentScan(basePackages = "com.weathair")
public class WeathAirApplication {
	
	@Autowired
	private TownshipController townshipController;
	
	@Autowired
	private MeteoIndicatorService meteoIndicatorService;
	
	@PostConstruct
	public void init()  throws IOException, InterruptedException {
		File file = new File("src/main/resources/Communes.csv");
		
		townshipController.postTownships(file);
		
		meteoIndicatorService.saveUpdateIndicatorsForOccitanie();
		
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(WeathAirApplication.class, args);

		
	}

}
