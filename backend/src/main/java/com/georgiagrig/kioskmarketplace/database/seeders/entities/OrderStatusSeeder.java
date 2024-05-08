package com.georgiagrig.kioskmarketplace.database.seeders.entities;
import com.georgiagrig.kioskmarketplace.database.factories.OrderStatusFactory;
import com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder;
import com.georgiagrig.kioskmarketplace.entities.OrderStatus;
import com.georgiagrig.kioskmarketplace.repositories.OrderStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderStatusSeeder implements BaseSeeder {
    @Autowired
    private OrderStatusFactory factory;

    @Autowired
    private OrderStatusRepository repository;

    public void seed() {
        log.info("OrderStatusSeeder seeder started ...");

        Iterable<OrderStatus> data = factory.make(10);

        for (OrderStatus object : data) {
            repository.save(object);
        }
    }
}
