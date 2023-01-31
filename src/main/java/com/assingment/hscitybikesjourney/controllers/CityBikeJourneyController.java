package com.assingment.hscitybikesjourney.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assingment.hscitybikesjourney.dto.CityBikeJourney;
import com.assingment.hscitybikesjourney.repositories.SearchRepository;
import com.assingment.hscitybikesjourney.services.CityBikeJourneyService;

@RestController
@RequestMapping("/api")
public class CityBikeJourneyController {

        @Autowired
        CityBikeJourneyService cityBikeJourneyService;

        @Autowired
        SearchRepository searchRepository;

        @GetMapping("/journeys")
        public ResponseEntity<Page<CityBikeJourney>> getAll(@RequestParam(required = false) Integer page,
                        @RequestParam(required = false) Integer size) {

                return ResponseEntity
                                .ok(cityBikeJourneyService.listAllBikeJourney(page, size));
        }

        @GetMapping("/journeys/search")
        public ResponseEntity<List<CityBikeJourney>> search(@RequestParam String text) {

                return ResponseEntity.ok(searchRepository.findByText(text));

        }

}
