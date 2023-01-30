package com.assingment.hscitybikesjourney.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
