package com.assingment.hscitybikesjourney.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assingment.hscitybikesjourney.dto.CityBikeJourney;

@Repository
public interface CityBikeJourneyRepository extends MongoRepository<CityBikeJourney, String> {

    @Override
    default Page<CityBikeJourney> findAll(Pageable pageable) {
        return findBy(pageable);

    }

    /*
     * Page<CityBikeJourney> findByDepartureStationName(String departureStationName,
     * Pageable pageable);
     * 
     * Page<CityBikeJourney> findByReturnStationName(String returnStationName,
     * Pageable pageable);
     * 
     * Page<CityBikeJourney> findByCoveredDistance(String coveredDistance, Pageable
     * pageable);
     * 
     * Page<CityBikeJourney> findByDuration(String duration, Pageable pageable);
     */

    Page<CityBikeJourney> findBy(Pageable pageable);
}