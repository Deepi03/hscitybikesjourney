package com.assingment.hscitybikesjourney.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assingment.hscitybikesjourney.dto.CityBikeJourney;
import com.assingment.hscitybikesjourney.helper.CSVHelper;
import com.assingment.hscitybikesjourney.repositories.CityBikeJourneyRepository;

@Service
public class CityBikeJourneyService {

    @Autowired
    CityBikeJourneyRepository cityBikeJourneyRepository;

    @Autowired
    CSVHelper csvHelper;

    public List<CityBikeJourney> saveData() {
        List<CityBikeJourney> validJourney = new ArrayList<>();
        try {
            List<List<String>> csvData = csvHelper.readFile();
            List<CityBikeJourney> allJourneyList = buildCityBikeJourneyObj(csvData);
            validJourney = filterValidJourney(allJourneyList);
        } catch (Exception e) {
            System.out.println("fail to store csv data: " + e.getMessage());
        }
        return validJourney;
    }

    public List<CityBikeJourney> buildCityBikeJourneyObj(List<List<String>> csvData) {
        List<CityBikeJourney> allJourney = new ArrayList<CityBikeJourney>();
        csvData.stream().forEach(r -> {
            CityBikeJourney cityBikeJourney = new CityBikeJourney(r.get(0), r.get(1), r.get(2), r.get(3), r.get(4),
                    r.get(5),
                    Long.parseLong(r.get(
                            6)),
                    Long.parseLong(r.get(
                            7)));
            allJourney.add(cityBikeJourney);
        });
        return allJourney;
    }

    public List<CityBikeJourney> filterValidJourney(List<CityBikeJourney> allJourney) {
        return allJourney.stream().filter(j -> isValidJourney(j)).toList();
    }

    private boolean isValidJourney(CityBikeJourney journey) {
        return journey.getDuration() > 10 && journey.getCoveredDistance() > 10;
    }

    public Page<CityBikeJourney> listAllBikeJourney(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);
        return cityBikeJourneyRepository.findAll(pageable);
    }

}
