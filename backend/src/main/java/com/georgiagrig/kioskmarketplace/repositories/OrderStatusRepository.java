package com.georgiagrig.kioskmarketplace.repositories;

import com.georgiagrig.kioskmarketplace.entities.OrderStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends BaseRepository<OrderStatus, Integer>{
}
