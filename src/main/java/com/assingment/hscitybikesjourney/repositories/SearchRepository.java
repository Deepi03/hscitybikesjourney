package com.assingment.hscitybikesjourney.repositories;

import java.util.List;

import com.assingment.hscitybikesjourney.dto.Journey;
import com.assingment.hscitybikesjourney.dto.Station;

public interface SearchRepository {

    List<Journey> searchJourney(String text);

    List<Station> searchStation(String text);

}
