package com.assingment.hscitybikesjourney.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assingment.hscitybikesjourney.dto.Journey;

@Repository
public interface JourneyRepository extends MongoRepository<Journey, String> {

    @Override
    default Page<Journey> findAll(Pageable pageable) {
        return findBy(pageable);

    }

    Page<Journey> findBy(Pageable pageable);

}