package com.georgiagrig.kioskmarketplace.repositories;

import com.georgiagrig.kioskmarketplace.entities.Basket;
import com.georgiagrig.kioskmarketplace.entities.CustomerOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository  extends BaseRepository<Basket, Integer> {
}
