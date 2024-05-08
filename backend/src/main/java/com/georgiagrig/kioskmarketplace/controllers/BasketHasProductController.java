package com.georgiagrig.kioskmarketplace.controllers;

import com.georgiagrig.kioskmarketplace.repositories.BasketHasProductRepository;
import com.georgiagrig.kioskmarketplace.requests.create.BasketHasProductCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.BasketHasProductPatchRequest;
import com.georgiagrig.kioskmarketplace.response.BasketHasProductResponse;
import com.georgiagrig.kioskmarketplace.services.BasketHasProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/basket_has_product")
public class BasketHasProductController {
    @Autowired
    private BasketHasProductService basketHasProductService;

    @Autowired
    private BasketHasProductRepository BasketHasProductRepository;

    // GET {server URI}/basketHasProduct
    @GetMapping(value = "")
    public Iterable<BasketHasProductResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return BasketHasProductResponse.map(basketHasProductService.list());
        } else {
            return BasketHasProductResponse.map(basketHasProductService.find(parameters));
        }
    }

    @GetMapping(value = "/{id}")
    public Iterable<BasketHasProductResponse> find(@PathVariable("id") Integer id) {
        return BasketHasProductResponse.map(basketHasProductService.find(id));
    }

    // CREATE {server URI}/customer_order
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<BasketHasProductResponse> create(@Valid @RequestBody BasketHasProductCreateRequest request) {
        Integer id = basketHasProductService.create(request);
        return find(id);
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<BasketHasProductResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody BasketHasProductPatchRequest request) {
        basketHasProductService.patch(id, request);
        return find(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        basketHasProductService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
