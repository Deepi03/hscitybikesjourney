package com.assingment.hscitybikesjourney.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assingment.hscitybikesjourney.dto.CityBikeJourney;
import com.assingment.hscitybikesjourney.services.CityBikeJourneyService;

@Controller
public class CityBikeJourneyController {

    @Autowired
    CityBikeJourneyService cityBikeJourneyService;

    /*
     * @GetMapping("/journeys")
     * public ResponseEntity<List<CityBikeJourney>> getAllJourneys() {
     * return ResponseEntity.ok(cityBikeJourneyService.listAllBikeJourney());
     * }
     */

    @GetMapping("/journeys")
    public ResponseEntity<Page<CityBikeJourney>> getAll(@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        return ResponseEntity
                .ok(cityBikeJourneyService.listAllBikeJourney(page, size));
    }

}
