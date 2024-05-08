package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.Basket;
import com.georgiagrig.kioskmarketplace.entities.User;
import com.georgiagrig.kioskmarketplace.repositories.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;


@Component
public class UserFactory extends BaseFactory<User> {

    public User make() {
        User item = new User();
        Basket basket = new Basket();
        item.setUsername(faker.name().username());
        item.setPassword(faker.internet().password());
        item.setName(faker.name().name());
        item.setPhone(faker.phoneNumber().toString());
        item.setEmail(faker.internet().emailAddress());
        item.setTotal_points(faker.random().nextInt(1000));
        item.setIs_administrator(faker.random().nextBoolean());
        basket.setUser(item);
        item.setBasket(basket);
        item.setCreatedAt(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return item;
    }

    public User generateAdministrator() {
        Basket basket = new Basket();
        User admin = new User();
        admin.setUsername("georgia");
        admin.setPassword("p");
        admin.setName("Georgia");
        admin.setPhone("123");
        admin.setEmail("georgia@gmail.com");
        admin.setTotal_points(100);
        admin.setIs_administrator(true);
        admin.setCreatedAt(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        basket.setUser(admin);
        admin.setBasket(basket);
        return admin;
    }
}
