package com.weathair;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.weathair.controllers.TownshipController;

@SpringBootApplication
@ComponentScan(basePackages = "com.weathair")
public class WeathAirApplication {
	
	@Autowired
	private TownshipController townshipController;
	
	@PostConstruct
	public void init()  throws IOException {
		File file = new File("src/main/resources/Communes.csv");
		
		townshipController.postTownships(file);
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(WeathAirApplication.class, args);

		
	}

}
