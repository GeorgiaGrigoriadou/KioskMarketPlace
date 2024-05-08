package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;

@Component
public class MeasurementUnitFactory extends BaseFactory<MeasurementUnit> {
    public MeasurementUnit make() {
        MeasurementUnit item = new MeasurementUnit();
        item.setCode(faker.lorem().word());
        item.setCreatedAt(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return item;
    }
}
