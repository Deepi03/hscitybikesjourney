package com.assingment.hscitybikesjourney.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assingment.hscitybikesjourney.dto.Station;

@Repository
public interface StationRespository extends MongoRepository<Station, String> {

    @Override
    default Page<Station> findAll(Pageable pageable) {
        return findBy(pageable);
    }

    Page<Station> findBy(Pageable pageable);
}
