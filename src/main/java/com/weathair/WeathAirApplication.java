package com.weathair;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.weathair.controllers.TownshipController;
import com.weathair.entities.Township;
import com.weathair.exceptions.TownshipException;
import com.weathair.repositories.TownshipRepository;
import com.weathair.services.AirIndicatorService;
import com.weathair.services.MeteoIndicatorService;

@SpringBootApplication
@ComponentScan(basePackages = "com.weathair")
public class WeathAirApplication {
	
	@Autowired
	private TownshipController townshipController;
	
	@Autowired
	private AirIndicatorService airIndicatorService;
	
	@Autowired
	private MeteoIndicatorService meteoIndicatorService;
	
	@PostConstruct
	public void init()  throws IOException, InterruptedException, TownshipException {
		File file = new File("src/main/resources/Communes.csv");
		
		townshipController.postTownships(file);
		
//		airIndicatorService.insertAirIndicatorsFromApiWaqi();
//		
//		meteoIndicatorService.saveUpdateIndicatorsForOccitanie();

	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(WeathAirApplication.class, args);

	}

}
