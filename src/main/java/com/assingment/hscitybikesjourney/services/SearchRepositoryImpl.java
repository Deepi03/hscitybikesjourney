package com.assingment.hscitybikesjourney.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.assingment.hscitybikesjourney.dto.Station;
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

    /**
     * implements SearchRepository interface and returns station based on the given
     * search text
     */

    public List<Station> searchStation(String text) {
        final List<Station> stations = new ArrayList<>();

        MongoDatabase database = client.getDatabase("helsinkicitybikejourneys");
        MongoCollection<Document> collection = database.getCollection("station");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", text)
                                .append("path", "station")))));

        result.forEach(doc -> stations.add(converter.read(Station.class, doc)));
        return stations;

    }
}
