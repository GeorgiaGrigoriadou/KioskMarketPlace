package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.BasketHasProduct;

import com.georgiagrig.kioskmarketplace.entities.QuizUserAnswer;
import org.springframework.stereotype.Component;

@Component
public class BasketHasProductFactory extends BaseFactory<BasketHasProduct> {
    public BasketHasProduct make() {
        BasketHasProduct item = new BasketHasProduct();
        item.setQuantity(faker.random().nextInt(5) + 1);
        return item;
    }
}
