package com.assingment.hscitybikesjourney.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "cityBikeJourney")
public class CityBikeJourney {

    private String departureTime;
    private String returnTime;
    private String departureStationId;
    private String departureStationName;
    private String returnStationId;
    private String returnStationName;
    private String coveredDistance;
    private String duration;

    public CityBikeJourney(String departureTime, String returnTime, String departureStationId,
            String departureStationName, String returnStationId, String returnStationName, String coveredDistance,
            String duration) {
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.departureStationId = departureStationId;
        this.departureStationName = departureStationName;
        this.returnStationId = returnStationId;
        this.returnStationName = returnStationName;
        this.coveredDistance = coveredDistance;
        this.duration = duration;
    }
}
