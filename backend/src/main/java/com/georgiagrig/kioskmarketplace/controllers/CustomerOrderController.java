package com.georgiagrig.kioskmarketplace.controllers;

import com.georgiagrig.kioskmarketplace.repositories.CustomerOrderRepository;
import com.georgiagrig.kioskmarketplace.requests.create.CustomerOrderCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.CustomerOrderPatchRequest;
import com.georgiagrig.kioskmarketplace.response.CustomerOrderResponse;
import com.georgiagrig.kioskmarketplace.services.CustomerOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customer_order")
public class CustomerOrderController {
    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    // GET {server URI}/customer_order
    @GetMapping(value = "")
    public Iterable<CustomerOrderResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return CustomerOrderResponse.map(customerOrderService.list());
        } else {
            return CustomerOrderResponse.map(customerOrderService.find(parameters));
        }
    }

    // CREATE {server URI}/customer_order
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<CustomerOrderResponse> create(@Valid @RequestBody CustomerOrderCreateRequest request) {
        Integer id = customerOrderService.create(request);
        return find(id);
    }

    // GET {server URI}/customer_order/1
    @GetMapping(value = "/{id}")
    public Iterable<CustomerOrderResponse> find(@PathVariable("id") Integer id) {
        return CustomerOrderResponse.map(customerOrderService.find(id));
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<CustomerOrderResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody CustomerOrderPatchRequest request) {
        customerOrderService.patch(id, request);
        return find(id);
    }

//    @PatchMapping(value = "/{id}/operation/complete")
//    public Iterable<CustomerOrderResponse> patch(@PathVariable("id") Integer id) {
//        CustomerOrderService.complete(id);
//        return find(id);
//    }
//
//    @PatchMapping(value = "/{id}/operation/pending")
//    public Iterable<CustomerOrderResponse> patch(@PathVariable("id") Integer id) {
//        CustomerOrderService.pending(id);
//        return find(id);
//    }
//
//    @PatchMapping(value = "/{id}/operation/cancel")
//    public Iterable<CustomerOrderResponse> patch(@PathVariable("id") Integer id) {
//        CustomerOrderService.cancel(id);
//        return find(id);
//    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        customerOrderService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
