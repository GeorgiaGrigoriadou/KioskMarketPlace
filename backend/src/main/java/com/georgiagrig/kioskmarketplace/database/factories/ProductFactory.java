package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.Product;


import org.springframework.stereotype.Component;


import java.time.ZoneId;
import java.util.ArrayList;

import static com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder.faker;
import static java.lang.Double.parseDouble;

@Component
public class ProductFactory extends BaseFactory<Product> {
    public Product make() {
        Product item = new Product();
        item.setName(faker.commerce().productName());
        item.setDescription(faker.lorem().sentence());
        item.setPrice(faker.number().randomDouble(2,0,30));
        item.setRetail_cost(faker.number().randomDouble(2,0,100));
        item.setCreatedAt(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return item;
    }
}
