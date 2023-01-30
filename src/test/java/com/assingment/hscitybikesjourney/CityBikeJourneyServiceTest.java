package com.assingment.hscitybikesjourney;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.assingment.hscitybikesjourney.dto.CityBikeJourney;
import com.assingment.hscitybikesjourney.services.CityBikeJourneyService;

@SpringBootTest
public class CityBikeJourneyServiceTest {

    @Autowired
    CityBikeJourneyService cityBikeJourneyService;

    List<String> journey1 = Arrays.asList("2021-05-31T23:57:25", "2021-06-01T00:05:46", "094", "Laajalahden aukio",
            "100", "Teljäntie", "2043", "8");
    List<String> journey2 = Arrays.asList("2021-05-31T23:57:25", "2021-06-01T00:05:46", "094", "Laajalahden aukio",
            "100", "Teletie", "2043", "13");
    List<String> journey3 = Arrays.asList("2021-05-31T23:57:25", "2021-06-01T00:05:46", "094", "Laajalahden aukio",
            "100", "Teljäntie", "2043", "200");

    List<List<String>> cityBikeJourneys = new ArrayList<>();

    @DisplayName("Test Spring @Autowired Integration")
    @Test

    void filterValidJourneyTest() {
        cityBikeJourneys.add(journey1);
        cityBikeJourneys.add(journey2);
        cityBikeJourneys.add(journey3);
        List<CityBikeJourney> allTestJourneys = cityBikeJourneyService.buildCityBikeJourneyObj(cityBikeJourneys);

        assertEquals(2, cityBikeJourneyService.filterValidJourney(allTestJourneys).size());
    }

}
