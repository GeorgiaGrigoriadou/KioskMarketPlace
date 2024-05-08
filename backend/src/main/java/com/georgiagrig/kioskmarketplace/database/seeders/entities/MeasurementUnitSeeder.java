package com.georgiagrig.kioskmarketplace.database.seeders.entities;

import com.georgiagrig.kioskmarketplace.database.factories.MeasurementUnitFactory;
import com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder;
import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.repositories.MeasurementUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MeasurementUnitSeeder implements BaseSeeder {

    @Autowired
    private MeasurementUnitFactory factory;

    @Autowired
    private MeasurementUnitRepository repository;

    public void seed() {
        log.info("MeasurementUnitSeeder seeder started ...");

        Iterable<MeasurementUnit> data = factory.make(10);

        for (MeasurementUnit object : data) {
            repository.save(object);
        }
    }
}
