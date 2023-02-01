package com.assingment.hscitybikesjourney.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "journey")

public class Journey {

    private String departureTime;
    private String returnTime;
    private String departureStationId;
    private String departureStation;
    private String returnStationId;
    private String returnStation;
    private Long coveredDistance;
    private Long duration;

}
