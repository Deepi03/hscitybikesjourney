
package com.assingment.hscitybikesjourney.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assingment.hscitybikesjourney.dto.Station;
import com.assingment.hscitybikesjourney.repositories.CityBikeStationRespository;

@Service
public class CityBikeStationService {
    @Autowired
    CityBikeStationRespository cityBikeStationRespository;

    public List<Station> getAllStations() {
        return cityBikeStationRespository.findAllStations();
    }
}