package com.assingment.hscitybikesjourney.repositories;

import java.util.List;

import com.assingment.hscitybikesjourney.dto.CityBikeJourney;

public interface SearchRepository {

    List<CityBikeJourney> findByText(String text);
}
