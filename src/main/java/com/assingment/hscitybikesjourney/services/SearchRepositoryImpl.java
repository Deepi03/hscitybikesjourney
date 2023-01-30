package com.assingment.hscitybikesjourney.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.assingment.hscitybikesjourney.dto.CityBikeJourney;
import com.assingment.hscitybikesjourney.repositories.SearchRepository;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class SearchRepositoryImpl implements SearchRepository {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    public List<CityBikeJourney> findByText(String text) {
        final List<CityBikeJourney> journeys = new ArrayList<>();

        MongoDatabase database = client.getDatabase("helsinkicitybikejourneys");
        MongoCollection<Document> collection = database.getCollection("cityBikeJourney");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", text)
                                .append("path", Arrays.asList("departureStationName",
                                        "returnStationName")))),
                new Document("$sort",
                        new Document("coveredDistance", 1L))));

        result.forEach(doc -> journeys.add(converter.read(CityBikeJourney.class, doc)));
        return journeys;

    }

}
