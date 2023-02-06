package com.assingment.hscitybikesjourney.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assingment.hscitybikesjourney.dto.Journey;
import com.assingment.hscitybikesjourney.helper.CSVHelper;
import com.assingment.hscitybikesjourney.repositories.JourneyRepository;
import com.assingment.hscitybikesjourney.repositories.SearchRepository;

@Service
public class JourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Autowired
    SearchRepository searchRepository;

    @Autowired
    CSVHelper csvHelper;

    /**
     * 
     * 
     * @return valid journey after validations such as no journeys that lasted for
     *         less than ten seconds and that covered distances shorter than 10
     *         meters
     */

    public List<Journey> saveDataToDB() {
        List<Journey> validJourney = new ArrayList<>();
        try {
            List<List<String>> csvData = csvHelper.readFile();
            List<Journey> allJourneyList = buildCityBikeJourneyObj(csvData);
            validJourney = filterValidJourney(allJourneyList);
        } catch (Exception e) {
            System.out.println("fail to store csv data: " + e.getMessage());
        }
        return validJourney;
    }

    /**
     * 
     * @param csvData
     * @return converted journey object list
     */

    public List<Journey> buildCityBikeJourneyObj(List<List<String>> csvData) {
        List<Journey> allJourney = new ArrayList<Journey>();
        csvData.stream().forEach(r -> {
            Journey cityBikeJourney = new Journey(r.get(0), r.get(1), r.get(2), r.get(3), r.get(4),
                    r.get(5),
                    Long.parseLong(r.get(
                            6)),
                    Long.parseLong(r.get(
                            7)));
            allJourney.add(cityBikeJourney);
        });
        return allJourney;
    }

    public List<Journey> filterValidJourney(List<Journey> allJourney) {
        return allJourney.stream().filter(j -> isValidJourney(j)).toList();
    }

    private boolean isValidJourney(Journey journey) {
        return journey.getDuration() > 10 && journey.getCoveredDistance() > 10;
    }

    /**
     * 
     * @param page
     * @param size
     * @return all journey from repository with pagination
     */

    public Page<Journey> listAllBikeJourney(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);

        return journeyRepository.findAll(pageable);
    }

    /**
     * @param text
     * @return journeys based on given search text
     */

    public List<Journey> searchJourney(String text) {

        System.out.println("search" + text);
        return searchRepository.searchJourney(text);

    }

}
