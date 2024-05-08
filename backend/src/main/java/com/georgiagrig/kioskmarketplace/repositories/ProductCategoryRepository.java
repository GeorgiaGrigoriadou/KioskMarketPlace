package com.georgiagrig.kioskmarketplace.repositories;


import com.georgiagrig.kioskmarketplace.entities.ProductCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends BaseRepository<ProductCategory, Integer> {
}

