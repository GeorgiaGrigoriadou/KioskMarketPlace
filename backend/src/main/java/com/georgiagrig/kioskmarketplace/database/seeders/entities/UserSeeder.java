package com.georgiagrig.kioskmarketplace.database.seeders.entities;

import com.georgiagrig.kioskmarketplace.database.factories.CustomerOrderFactory;
import com.georgiagrig.kioskmarketplace.database.factories.OrderStatusFactory;
import com.georgiagrig.kioskmarketplace.database.factories.UserFactory;
import com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder;
import com.georgiagrig.kioskmarketplace.entities.*;
import com.georgiagrig.kioskmarketplace.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class UserSeeder implements BaseSeeder {
    @Autowired
    private UserFactory factory;

    @Autowired
    private UserRepository repository;

    @Autowired
    private BasketRepository basketRepository;

    public void seed() {
        log.info("UserSeeder seeder started ...");

        Iterable<User> users = factory.make(10);

        if (!repository.findById(1).isPresent()) {
            User user = factory.generateAdministrator();

            repository.save(user);
        }


        for (User user : users) {
            int n = new Random().nextInt(1, 3);
            repository.save(user);
        }

    }

}
