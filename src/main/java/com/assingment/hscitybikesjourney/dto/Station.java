package com.assingment.hscitybikesjourney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    private String station;
    private Integer numberOfDepartures;
    private Integer numberOfReturns;
}
