package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;
@Component
public class OrderStatusFactory extends BaseFactory<OrderStatus> {
    public OrderStatus make() {
        OrderStatus item = new OrderStatus();
        item.setDescription(faker.lorem().paragraph());
        item.setCreatedAt(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return item;
    }
}
