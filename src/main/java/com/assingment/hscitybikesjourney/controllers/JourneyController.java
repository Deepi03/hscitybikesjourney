package com.assingment.hscitybikesjourney.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assingment.hscitybikesjourney.dto.Journey;

import com.assingment.hscitybikesjourney.services.JourneyService;

/**
 * endpoint for journeys
 * 
 * "api/journeys"
 */
@RestController
@RequestMapping("/api")
public class JourneyController {

        @Autowired
        JourneyService journeyService;

        /***
         * get all the journeys with pagiantion
         * 
         * @param page optional
         * @param size optional
         * @return
         */

        @GetMapping("/journeys")
        public ResponseEntity<Page<Journey>> getAll(@RequestParam(required = false) Integer page,
                        @RequestParam(required = false) Integer size) {

                return ResponseEntity
                                .ok(journeyService.listAllBikeJourney(page, size));
        }

}
