package com.assingment.hscitybikesjourney;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.assingment.hscitybikesjourney.repositories.JourneyRepository;
import com.assingment.hscitybikesjourney.repositories.StationRespository;
import com.assingment.hscitybikesjourney.services.JourneyService;
import com.assingment.hscitybikesjourney.services.StationService;

import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HscitybikesjourneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HscitybikesjourneyApplication.class, args);
	}

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	JourneyRepository journeyRepository;

	@Autowired
	JourneyService journeyService;

	@Autowired
	StationService stationService;

	@Autowired
	StationRespository stationRespository;

	@Bean
	InitializingBean sendDatabase() {

		mongoTemplate.remove(new Query(), "journey");
		mongoTemplate.remove(new Query(), "station");

		return () -> {
			journeyRepository.saveAll(journeyService.saveData());
			stationRespository.saveAll(stationService.findAllStationsFromJourney());

		};
	}
}
