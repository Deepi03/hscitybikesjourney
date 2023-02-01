package com.assingment.hscitybikesjourney.repositories;

import java.util.List;

import com.assingment.hscitybikesjourney.dto.Station;

public interface SearchRepository {

    List<Station> searchStation(String text);

}
