package com.georgiagrig.kioskmarketplace.database.factories;
import com.georgiagrig.kioskmarketplace.entities.ProductCategory;

import org.springframework.stereotype.Component;


import java.time.ZoneId;
import java.util.ArrayList;

@Component
public class ProductCategoryFactory extends BaseFactory<ProductCategory> {
    public ProductCategory make() {
        ProductCategory item = new ProductCategory();
        item.setName(faker.lorem().word());
        item.setCreatedAt(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return item;
    }
}
