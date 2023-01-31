package com.assingment.hscitybikesjourney.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.assingment.hscitybikesjourney.dto.Station;

@Repository
public interface CityBikeStationRespository {

    List<Station> findAllStations();
}
