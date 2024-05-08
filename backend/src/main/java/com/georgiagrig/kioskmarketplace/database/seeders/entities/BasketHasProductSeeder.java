package com.georgiagrig.kioskmarketplace.database.seeders.entities;

import com.georgiagrig.kioskmarketplace.database.factories.BasketHasProductFactory;
import com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder;
import com.georgiagrig.kioskmarketplace.entities.BasketHasProduct;
import com.georgiagrig.kioskmarketplace.entities.QuizUserAnswer;
import com.georgiagrig.kioskmarketplace.repositories.BasketHasProductRepository;
import com.georgiagrig.kioskmarketplace.repositories.BasketRepository;
import com.georgiagrig.kioskmarketplace.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BasketHasProductSeeder implements BaseSeeder {

    @Autowired
    private BasketHasProductRepository basketHasProductRepository;

    @Autowired
    private BasketHasProductFactory basketHasProductFactory;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketRepository basketRepository;

    public void seed() {
        log.info("BasketHasProduct seeder started ...");
        Iterable<BasketHasProduct> data = basketHasProductFactory.make(10);

        for (BasketHasProduct basketHasProduct : data) {
            basketHasProduct.setProduct(productRepository.random());
            basketHasProduct.setBasket(basketRepository.random());
            basketHasProductRepository.save(basketHasProduct);

        }




    }
}
