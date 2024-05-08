package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.entities.SpecialOffer;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;

@Component
public class SpecialOfferFactory extends BaseFactory<SpecialOffer> {
    public SpecialOffer make() {
        SpecialOffer item = new SpecialOffer();
        item.setPoints(faker.random().nextInt(0, 30));
        item.setDiscount(faker.random().nextInt(1, 6) * 5.0);
        item.setDateStart(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        item.setDateEnd(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        item.setCreatedAt(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return item;
    }
}
