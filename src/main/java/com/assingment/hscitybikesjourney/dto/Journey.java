package com.assingment.hscitybikesjourney.dto;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Document(collection = "journey")
@Data
@RequiredArgsConstructor
public class Journey {
    @Id
    private String id;
    private final String departureTime;
    private final String returnTime;
    private final String departureStationId;
    private final String departureStation;
    private final String returnStationId;
    private final String returnStation;
    private final Long coveredDistance;
    private final Long duration;

}
