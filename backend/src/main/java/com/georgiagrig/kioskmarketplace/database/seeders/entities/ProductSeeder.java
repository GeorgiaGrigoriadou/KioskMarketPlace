package com.georgiagrig.kioskmarketplace.database.seeders.entities;

import com.georgiagrig.kioskmarketplace.database.factories.ProductFactory;
import com.georgiagrig.kioskmarketplace.database.factories.SpecialOfferFactory;
import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.entities.Product;
import com.georgiagrig.kioskmarketplace.entities.ProductCategory;
import com.georgiagrig.kioskmarketplace.entities.SpecialOffer;
import com.georgiagrig.kioskmarketplace.repositories.ProductRepository;
import com.georgiagrig.kioskmarketplace.repositories.SpecialOfferRepository;
import com.georgiagrig.kioskmarketplace.repositories.ProductCategoryRepository;
import com.georgiagrig.kioskmarketplace.repositories.MeasurementUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class ProductSeeder {

    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private SpecialOfferFactory specialOfferFactory;

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private MeasurementUnitRepository measurementUnitRepository;

    @Autowired
    private ProductRepository productRepository;

    public void seed() {
        log.info("ProductSeeder seeder started ...");

        // Create and save multiple Product entities using the ProductFactory
        Iterable<Product> products = productFactory.make(10); // Create 10 Product entities

        for (Product product : products) {
            product.setMeasurementUnit(measurementUnitRepository.random());
            product.setProductCategory(productCategoryRepository.random());

            int n = new Random().nextInt(0,3);

            Iterable<SpecialOffer> offers = specialOfferFactory.make(n);

            for (SpecialOffer offer : offers) {
                product.getSpecialOffers().add(offer);
                offer.setProduct(product);
            }

            productRepository.save(product);

            for (SpecialOffer offer : product.getSpecialOffers()) {
                specialOfferRepository.save(offer);
            }
        }
    }

}
































