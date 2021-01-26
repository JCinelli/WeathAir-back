package com.weathair;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.weathair.controllers.TownshipController;
import com.weathair.exceptions.TownshipException;
import com.weathair.services.AirIndicatorService;
import com.weathair.services.MeteoIndicatorService;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.weathair")
public class WeathAirApplication {

	@Autowired
	private TownshipController townshipController;

	@Autowired
	private AirIndicatorService airIndicatorService;

	@Autowired
	private MeteoIndicatorService meteoIndicatorService;

	@PostConstruct
	public void init() throws IOException, InterruptedException, TownshipException {
		File file = new File("src/main/resources/Communes.csv");

//		townshipController.postTownships(file);

//		airIndicatorService.insertAirIndicatorsFromApiWaqi();

//  	meteoIndicatorService.saveUpdateIndicatorsForOccitanie();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "POST", "PATCH", "PUT", "OPTIONS")
				.allowCredentials(true);
			}
		};
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(WeathAirApplication.class, args);

	}

}
