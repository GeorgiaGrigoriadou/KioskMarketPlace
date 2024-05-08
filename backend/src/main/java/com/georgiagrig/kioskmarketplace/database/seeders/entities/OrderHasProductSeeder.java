package com.georgiagrig.kioskmarketplace.database.seeders.entities;


import com.georgiagrig.kioskmarketplace.database.factories.OrderHasProductFactory;
import com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder;
import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.entities.OrderHasProduct;
import com.georgiagrig.kioskmarketplace.repositories.CustomerOrderRepository;
import com.georgiagrig.kioskmarketplace.repositories.OrderHasProductRepository;
import com.georgiagrig.kioskmarketplace.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderHasProductSeeder implements BaseSeeder {
    @Autowired
    private OrderHasProductFactory orderHasProductFactory;

    @Autowired
    private OrderHasProductRepository orderHasProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    public void seed() {
        log.info("OrderHasProductSeeder seeder started ...");

        Iterable<OrderHasProduct> data = orderHasProductFactory.make(10);

        for (OrderHasProduct orderHasProduct : data) {
            orderHasProduct.setOrder(customerOrderRepository.random());
            orderHasProduct.setProduct(productRepository.random());
            orderHasProductRepository.save(orderHasProduct);

        }
    }
}
