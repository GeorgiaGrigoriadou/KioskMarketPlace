package com.georgiagrig.kioskmarketplace.database.seeders.entities;
import com.georgiagrig.kioskmarketplace.database.factories.ProductCategoryFactory;
import com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder;
import com.georgiagrig.kioskmarketplace.entities.ProductCategory;
import com.georgiagrig.kioskmarketplace.repositories.ProductCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Slf4j
public class ProductCategorySeeder implements BaseSeeder {

    @Autowired
    private ProductCategoryFactory factory;

    @Autowired
    private ProductCategoryRepository repository;

    public void seed() {
        log.info("ProductCategorySeeder seeder started ...");

        Iterable<ProductCategory> data = factory.make(10);

        for (ProductCategory object : data) {
            repository.save(object);
        }


    }
}
