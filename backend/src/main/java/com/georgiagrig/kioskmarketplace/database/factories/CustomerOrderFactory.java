package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.CustomerOrder;

import org.springframework.stereotype.Component;
import java.time.ZoneId;
import java.util.ArrayList;

import static com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder.faker;

@Component
public class CustomerOrderFactory extends BaseFactory<CustomerOrder> {
    public CustomerOrder make() {
        CustomerOrder item = new CustomerOrder();
        item.setTotal_price(faker.number().randomDouble(2,0,100));
        item.setCreatedAt(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return item;
    }
}
