package com.assingment.hscitybikesjourney.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.assingment.hscitybikesjourney.dto.ModelMongo;
import com.assingment.hscitybikesjourney.dto.Station;
import com.assingment.hscitybikesjourney.repositories.CityBikeStationRespository;
import com.google.gson.Gson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

@Component
public class CityBikeStationImpl implements CityBikeStationRespository {
    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    List<Station> stations = new ArrayList<>();
    List<Station> returnStations = new ArrayList<>();
    Gson gson = new Gson();

    @Override
    public List<Station> findAllStations() {
        MongoDatabase database = client.getDatabase("helsinkicitybikejourneys");
        MongoCollection<Document> collection = database.getCollection("cityBikeJourney");

        addDepartureStation(collection);
        addReturnStation(collection);

        stations.addAll(returnStations);
        return stations;
    }

    public void addDepartureStation(MongoCollection<Document> collection) {
        collection.aggregate(Arrays.asList(Aggregates.group("$departureStation", Accumulators.sum("count", 1))))
                .forEach(doc -> {

                    ModelMongo depStationNameAndCount = gson.fromJson(doc.toJson(), ModelMongo.class);
                    stations.add(new Station(depStationNameAndCount.get_id(),
                            depStationNameAndCount.getCount(), 0));
                });
    }

    public void addReturnStation(MongoCollection<Document> collection) {
        collection.aggregate(
                Arrays.asList(
                        Aggregates.group("$returnStation", Accumulators.sum("count", 1))))
                .forEach(doc -> {
                    ModelMongo returnStationNameAndCount = gson.fromJson(doc.toJson(), ModelMongo.class);
                    stations.stream().forEach(res -> {
                        if (res.getStation().equalsIgnoreCase(returnStationNameAndCount.get_id())) {
                            res.setNumberOfReturns(returnStationNameAndCount.getCount());
                        } else {
                            returnStations.add(new Station(returnStationNameAndCount.get_id(), 0,
                                    returnStationNameAndCount.getCount()));
                        }
                    });
                });
    }

}
