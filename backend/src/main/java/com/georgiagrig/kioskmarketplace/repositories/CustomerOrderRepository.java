package com.georgiagrig.kioskmarketplace.repositories;
import com.georgiagrig.kioskmarketplace.entities.CustomerOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository  extends BaseRepository<CustomerOrder, Integer>{
}
