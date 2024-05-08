package com.georgiagrig.kioskmarketplace.database.seeders;

import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.entities.OrderStatus;
import com.georgiagrig.kioskmarketplace.entities.ProductCategory;
import com.georgiagrig.kioskmarketplace.repositories.MeasurementUnitRepository;
import com.georgiagrig.kioskmarketplace.repositories.OrderStatusRepository;
import com.georgiagrig.kioskmarketplace.repositories.ProductCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class ProductionSeeder implements BaseSeeder {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private MeasurementUnitRepository measurementUnitRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    private void initializeOrderStatus() {
        if (!orderStatusRepository.findById(1).isPresent()) {
            OrderStatus status = new OrderStatus();
            status.setId(1);
            status.setDescription("pending");
            orderStatusRepository.save(status);
        }

        if (!orderStatusRepository.findById(2).isPresent()) {
            OrderStatus status = new OrderStatus();
            status.setId(2);
            status.setDescription("cancelled");
            orderStatusRepository.save(status);
        }

        if (!orderStatusRepository.findById(3).isPresent()) {
            OrderStatus status = new OrderStatus();
            status.setId(3);
            status.setDescription("completed");
            orderStatusRepository.save(status);
        }
    }

    private void initializeMeasurementUnits() {
        if (!measurementUnitRepository.findById(1).isPresent()) {
            MeasurementUnit piece = new MeasurementUnit();
            piece.setId(1);
            piece.setCode("piece");
            piece.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
            measurementUnitRepository.save(piece);
        }
    }

    private void initializeProductCategories() {
        List<ProductCategory> categoryList = new ArrayList<>();
        categoryList.add(new ProductCategory(1, "Cigarettes", LocalDateTime.now(ZoneId.systemDefault()), new ArrayList<>()));
        categoryList.add(new ProductCategory(2, "Tobbacos", LocalDateTime.now(ZoneId.systemDefault()), new ArrayList<>()));
        categoryList.add(new ProductCategory(3, "Vapes", LocalDateTime.now(ZoneId.systemDefault()), new ArrayList<>()));
        categoryList.add(new ProductCategory(4, "Snacks", LocalDateTime.now(ZoneId.systemDefault()), new ArrayList<>()));
        categoryList.add(new ProductCategory(5, "Drinks", LocalDateTime.now(ZoneId.systemDefault()), new ArrayList<>()));
        categoryList.add(new ProductCategory(6, "Ice cream", LocalDateTime.now(ZoneId.systemDefault()), new ArrayList<>()));
        categoryList.add(new ProductCategory(7, "Heated Tobbaco products", LocalDateTime.now(ZoneId.systemDefault()), new ArrayList<>()));

        for (ProductCategory category : categoryList) {
            if (!productCategoryRepository.findById(category.getId()).isPresent()) {
                productCategoryRepository.save(category);
            }
        }
    }

    public void seed() {
        log.info("Production seeder started ...");

        initializeOrderStatus();
        initializeMeasurementUnits();
        initializeProductCategories();
    }
}
