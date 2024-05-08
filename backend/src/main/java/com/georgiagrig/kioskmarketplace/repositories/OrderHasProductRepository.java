package com.georgiagrig.kioskmarketplace.repositories;

import com.georgiagrig.kioskmarketplace.entities.OrderHasProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHasProductRepository extends BaseRepository<OrderHasProduct, Integer> {
}
