package com.assingment.hscitybikesjourney.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "station")
public class Station {
    private String station;
    private Integer numberOfDepartures;
    private Integer numberOfReturns;
}
