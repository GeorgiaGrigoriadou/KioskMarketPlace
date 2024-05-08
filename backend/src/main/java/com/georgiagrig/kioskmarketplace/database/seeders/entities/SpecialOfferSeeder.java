package com.georgiagrig.kioskmarketplace.database.seeders.entities;

import com.georgiagrig.kioskmarketplace.database.factories.SpecialOfferFactory;
import com.georgiagrig.kioskmarketplace.entities.SpecialOffer;
import com.georgiagrig.kioskmarketplace.repositories.SpecialOfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpecialOfferSeeder {
    @Autowired
    private SpecialOfferFactory factory;

    @Autowired
    private SpecialOfferRepository repository;

    public void seed() {
        log.info("SpecialOfferSeeder seeder started ...");

        Iterable<SpecialOffer> data = factory.make(10);

        for (SpecialOffer object : data) {
            repository.save(object);
        }
    }
}
