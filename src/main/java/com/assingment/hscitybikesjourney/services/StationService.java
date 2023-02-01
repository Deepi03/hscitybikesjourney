package com.assingment.hscitybikesjourney.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.assingment.hscitybikesjourney.dto.ModelMongo;
import com.assingment.hscitybikesjourney.dto.Station;
import com.assingment.hscitybikesjourney.repositories.SearchRepository;
import com.assingment.hscitybikesjourney.repositories.StationRespository;
import com.google.gson.Gson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

@Component
public class StationService {
    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Autowired
    StationRespository cityBikeStationRespository;

    @Autowired
    SearchRepository searchRepository;

    Gson gson = new Gson();

    /**
     * @return found stations from journey and returns disticnt station with number
     *         of returns and departures
     */

    public List<Station> findAllStationsFromJourney() {
        MongoDatabase database = client.getDatabase("helsinkicitybikejourneys");
        MongoCollection<Document> collection = database.getCollection("journey");

        List<Station> departueStations = groupByStationType(collection, "departureStation");
        List<Station> returnStations = groupByStationType(collection, "returnStation");
        List<Station> stations = buildUniqueStations(departueStations, returnStations);

        return stations;
    }

    /**
     * @param departueStations
     * @param returnStations
     * @return list of unique stations
     *         each station object has station name with number of departures and
     *         returns
     */

    public List<Station> buildUniqueStations(List<Station> departueStations, List<Station> returnStations) {
        List<Station> allStations = new ArrayList<>();

        allStations.addAll(departueStations);

        returnStations.forEach(returnStation -> {

            Station foundStation = allStations.stream()
                    .filter(as -> as.getStation().equalsIgnoreCase(returnStation.getStation())).findFirst()
                    .orElse(null);

            if (foundStation == null) {
                allStations.add(returnStation);
            } else {
                foundStation.setNumberOfReturns(returnStation.getNumberOfReturns());
                // allStations.add(foundStation);
            }

        });
        return allStations;

    }

    /**
     * 
     * @param collection
     * @param stationType
     * @return list of return stations when return station type is passed
     *         return List of departure stations when departure station type is
     *         passed
     */

    private List<Station> groupByStationType(MongoCollection<Document> collection, String stationType) {
        List<Station> stations = new ArrayList<>();
        collection.aggregate(Arrays.asList(Aggregates.group(
                "$" + stationType, Accumulators.sum("count", 1))))
                .forEach(doc -> {
                    ModelMongo stationNameAndCount = gson.fromJson(doc.toJson(), ModelMongo.class);
                    if (stationType.equals("departureStation")) {
                        stations.add(new Station(stationNameAndCount.get_id(),
                                stationNameAndCount.getCount(), 0));
                    } else {
                        stations.add(new Station(stationNameAndCount.get_id(), 0,
                                stationNameAndCount.getCount()));
                    }

                });
        return stations;
    }

    /**
     * @param page
     * @param size
     * @return all stations from repository with pagination
     */

    public Page<Station> findAllStations(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);
        return cityBikeStationRespository.findAll(pageable);
    }

    /**
     * @param text
     * @return single station based on given search text
     */

    public List<Station> searchStation(String text) {

        return searchRepository.searchStation(text);

    }

}
