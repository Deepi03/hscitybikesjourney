package com.assingment.hscitybikesjourney.dto;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "station")
public class Station {
    @Id
    private String id;
    private String station;
    private Integer numberOfDepartures;
    private Integer numberOfReturns;

    public Station(String station, Integer numberOfDepartures, Integer numberOfReturns) {
        this.station = station;
        this.numberOfDepartures = numberOfDepartures;
        this.numberOfReturns = numberOfReturns;
    }

}
