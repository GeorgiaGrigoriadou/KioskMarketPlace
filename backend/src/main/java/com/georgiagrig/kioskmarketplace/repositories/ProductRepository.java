package com.georgiagrig.kioskmarketplace.repositories;

import com.georgiagrig.kioskmarketplace.entities.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends  BaseRepository<Product, Integer> {
}
