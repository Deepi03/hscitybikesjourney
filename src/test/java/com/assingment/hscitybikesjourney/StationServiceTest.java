package com.assingment.hscitybikesjourney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.assingment.hscitybikesjourney.dto.Station;
import com.assingment.hscitybikesjourney.services.StationService;

@SpringBootTest
public class StationServiceTest {

    @Autowired
    StationService stationService;

    @Test
    void buildUniqueStationsTest() {

        List<Station> departureStations = new ArrayList<>();
        List<Station> returnStations = new ArrayList<>();
        List<Integer> returnNumber = new ArrayList<>();

        departureStations.add(new Station("leppäväära", 5, 0));
        departureStations.add(new Station("kilo", 2, 0));

        returnStations.add(new Station("leppäväära", 0, 1));
        returnStations.add(new Station("mäti", 0, 8));

        List<Station> res = stationService.buildUniqueStations(departureStations, returnStations);
        res.stream().forEach(r -> {
            returnNumber.add(r.getNumberOfReturns());
        });

        assertEquals(3, res.size(), "stations unique count matches");

        assertTrue(returnNumber.contains(8), "station with 9 returns ");

    }

}