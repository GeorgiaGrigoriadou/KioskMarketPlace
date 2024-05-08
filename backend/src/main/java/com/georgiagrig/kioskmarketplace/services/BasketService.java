package com.georgiagrig.kioskmarketplace.services;
import com.georgiagrig.kioskmarketplace.entities.Basket;
import com.georgiagrig.kioskmarketplace.entities.CustomerOrder;
import com.georgiagrig.kioskmarketplace.repositories.BasketRepository;
import com.georgiagrig.kioskmarketplace.repositories.CustomerOrderRepository;
import com.georgiagrig.kioskmarketplace.repositories.OrderStatusRepository;
import com.georgiagrig.kioskmarketplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BasketService {
    @Autowired
    private BasketRepository repository;

    public Iterable<Basket> find(Integer id) {
        Basket result = repository.findByIdOrFail(id);

        ArrayList<Basket> list = new ArrayList<>();
        list.add(result);

        return list;
    }
}
