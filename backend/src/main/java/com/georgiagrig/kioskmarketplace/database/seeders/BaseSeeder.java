package com.georgiagrig.kioskmarketplace.database.seeders;

import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.entities.ProductCategory;
import com.georgiagrig.kioskmarketplace.entities.SpecialOffer;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Component
public interface BaseSeeder {
    final Faker faker = new Faker(new Locale("en-US"));


}
