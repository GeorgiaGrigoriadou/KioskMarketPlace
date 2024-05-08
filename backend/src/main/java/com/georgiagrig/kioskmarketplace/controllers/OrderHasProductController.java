package com.georgiagrig.kioskmarketplace.controllers;
import com.georgiagrig.kioskmarketplace.repositories.OrderHasProductRepository;
import com.georgiagrig.kioskmarketplace.requests.create.OrderHasProductCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.OrderHasProductPatchRequest;
import com.georgiagrig.kioskmarketplace.response.OrderHasProductResponse;
import com.georgiagrig.kioskmarketplace.services.OrderHasProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order_has_product")
public class OrderHasProductController {
    @Autowired
    private OrderHasProductService orderHasProductService;

    @Autowired
    private OrderHasProductRepository orderHasProductRepository;

    // GET {server URI}/customer_order
    @GetMapping(value = "")
    public Iterable<OrderHasProductResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return OrderHasProductResponse.map(orderHasProductService.list());
        } else {
            return OrderHasProductResponse.map(orderHasProductService.find(parameters));
        }
    }

    // CREATE {server URI}/customer_order
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<OrderHasProductResponse> create(@Valid @RequestBody OrderHasProductCreateRequest request) {
        Integer id = orderHasProductService.create(request);
        return find(id);
    }

    // GET {server URI}/customer_order/1
    @GetMapping(value = "/{id}")
    public Iterable<OrderHasProductResponse> find(@PathVariable("id") Integer id) {
        return OrderHasProductResponse.map(orderHasProductService.find(id));
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<OrderHasProductResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody OrderHasProductPatchRequest request) {
        orderHasProductService.patch(id, request);
        return find(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        orderHasProductService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
