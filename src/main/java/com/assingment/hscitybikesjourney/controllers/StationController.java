package com.assingment.hscitybikesjourney.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assingment.hscitybikesjourney.dto.Station;

import com.assingment.hscitybikesjourney.services.StationService;

@RestController
@RequestMapping("/api")
public class StationController {

    @Autowired
    StationService stationService;

    @GetMapping("/stations")
    public ResponseEntity<Page<Station>> getAllStations(@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        return ResponseEntity
                .ok(stationService.findAllStations(page, size));
    }

    @GetMapping("/stations/search")
    public ResponseEntity<List<Station>> search(@RequestParam String text) {

        return ResponseEntity.ok(stationService.searchJourney(text));

    }

}
