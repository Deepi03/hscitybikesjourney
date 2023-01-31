package com.assingment.hscitybikesjourney.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assingment.hscitybikesjourney.dto.Station;

import com.assingment.hscitybikesjourney.services.CityBikeStationService;

@RestController
@ReqestMapping("/api")
public class StationController {

    @Autowired
    CityBikeStationService cityBikeStationService;

    @GetMapping("/stations")
    public ResponseEntity<List<Station>> getAllStations() {
        return ResponseEntity
                .ok(cityBikeStationService.getAllStations());
    }

}
