package com.assingment.hscitybikesjourney;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.assingment.hscitybikesjourney.repositories.CityBikeJourneyRepository;
import com.assingment.hscitybikesjourney.services.CityBikeJourneyService;

import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HscitybikesjourneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HscitybikesjourneyApplication.class, args);
	}

	@Autowired
	CityBikeJourneyRepository cityBikeJourneyRepository;

	@Autowired
	CityBikeJourneyService cityBikeJourneyService;

	@Autowired
	MongoTemplate mongoTemplate;

	@Bean
	InitializingBean sendDatabase() {

		mongoTemplate.remove(new Query(), "cityBikeJourney");

		return () -> {
			cityBikeJourneyRepository.saveAll(cityBikeJourneyService.saveData());
		};
	}
}
