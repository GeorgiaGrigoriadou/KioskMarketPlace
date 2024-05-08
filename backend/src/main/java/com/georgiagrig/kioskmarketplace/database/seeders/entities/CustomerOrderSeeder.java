package com.georgiagrig.kioskmarketplace.database.seeders.entities;
import com.georgiagrig.kioskmarketplace.database.factories.CustomerOrderFactory;
import com.georgiagrig.kioskmarketplace.database.factories.OrderStatusFactory;
import com.georgiagrig.kioskmarketplace.database.factories.UserFactory;
import com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder;
import com.georgiagrig.kioskmarketplace.entities.*;
import com.georgiagrig.kioskmarketplace.repositories.CustomerOrderRepository;
import com.georgiagrig.kioskmarketplace.repositories.OrderStatusRepository;
import com.georgiagrig.kioskmarketplace.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class CustomerOrderSeeder implements BaseSeeder{

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderStatusFactory orderStatusFactory;

    @Autowired
    private CustomerOrderFactory orderFactory;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private CustomerOrderRepository orderRepository;

    public void seed() {
        log.info("UserSeeder seeder started ...");
            Iterable<CustomerOrder> orders = orderFactory.make(10); // Create 10 Product entities
            for (CustomerOrder order : orders) {
                order.setUser(userRepository.random());
                order.setOrderStatus(orderStatusRepository.random());
                orderRepository.save(order);
            }

    }
}
