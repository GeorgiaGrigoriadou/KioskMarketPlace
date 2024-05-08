package com.georgiagrig.kioskmarketplace.controllers;

import com.georgiagrig.kioskmarketplace.repositories.OrderStatusRepository;
import com.georgiagrig.kioskmarketplace.requests.create.OrderStatusCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.OrderStatusPatchRequest;
import com.georgiagrig.kioskmarketplace.response.OrderStatusResponse;
import com.georgiagrig.kioskmarketplace.services.OrderStatusService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order_status")
public class OrderStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    // GET {server URI}/order_status
    @GetMapping(value = "")
    public Iterable<OrderStatusResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return OrderStatusResponse.map(orderStatusService.list());
        } else {
            return OrderStatusResponse.map(orderStatusService.find(parameters));
        }
    }

    // CREATE {server URI}/order_status
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<OrderStatusResponse> create(@Valid @RequestBody OrderStatusCreateRequest request) {
        Integer id = orderStatusService.create(request);
        return find(id);
    }

    // GET {server URI}/order_status/1
    @GetMapping(value = "/{id}")
    public Iterable<OrderStatusResponse> find(@PathVariable("id") Integer id) {
        return OrderStatusResponse.map(orderStatusService.find(id));
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<OrderStatusResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody OrderStatusPatchRequest request) {
        orderStatusService.patch(id, request);
        return find(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        orderStatusService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
