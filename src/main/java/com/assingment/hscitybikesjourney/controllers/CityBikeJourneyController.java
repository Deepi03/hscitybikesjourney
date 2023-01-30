package com.assingment.hscitybikesjourney.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.assingment.hscitybikesjourney.dto.CityBikeJourney;
import com.assingment.hscitybikesjourney.payload.response.CityBikeJourneyResponse;
import com.assingment.hscitybikesjourney.services.CityBikeJourneyService;

@Controller
public class CityBikeJourneyController {

    @Autowired
    CityBikeJourneyService cityBikeJourneyService;

    @GetMapping("/journeys")
    public ResponseEntity<List<CityBikeJourney>> getAllJourneys() {
        return ResponseEntity.ok(cityBikeJourneyService.listAllBikeJourney());
    }
}
