package com.georgiagrig.kioskmarketplace.database.factories;
import com.georgiagrig.kioskmarketplace.entities.OrderHasProduct;
import org.springframework.stereotype.Component;

@Component
public class OrderHasProductFactory extends BaseFactory<OrderHasProduct> {
    public OrderHasProduct make() {
        OrderHasProduct item = new OrderHasProduct();
        item.setQuantity(faker.random().nextInt(10));
        return item;
    }
}
