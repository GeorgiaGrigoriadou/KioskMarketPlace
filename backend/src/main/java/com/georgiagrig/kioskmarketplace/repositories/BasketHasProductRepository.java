package com.georgiagrig.kioskmarketplace.repositories;

import com.georgiagrig.kioskmarketplace.entities.BasketHasProduct;
import com.georgiagrig.kioskmarketplace.entities.CustomerOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketHasProductRepository  extends BaseRepository<BasketHasProduct, Integer> {
}
