package com.georgiagrig.kioskmarketplace.controllers;

import com.georgiagrig.kioskmarketplace.entities.Basket;
import com.georgiagrig.kioskmarketplace.entities.User;
import com.georgiagrig.kioskmarketplace.repositories.UserRepository;
import com.georgiagrig.kioskmarketplace.requests.create.UserCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.UserPatchRequest;
import com.georgiagrig.kioskmarketplace.response.BasketResponse;
import com.georgiagrig.kioskmarketplace.response.UserResponse;
import com.georgiagrig.kioskmarketplace.services.BasketService;
import com.georgiagrig.kioskmarketplace.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    private BasketService basketService;

    @Autowired
    private UserRepository repository;

    @GetMapping(value = "/{id}")
    public Iterable<BasketResponse> find(@PathVariable("id") Integer id) {
        return BasketResponse.map(basketService.find(id));
    }

    @GetMapping(value = "/user/{id}")
    public BasketResponse findByUserId(@PathVariable("id") Integer userId) {
        User user = repository.findByIdOrFail(userId);
        Basket basket = user.getBasket();
        return BasketResponse.map(basket);
    }
}
