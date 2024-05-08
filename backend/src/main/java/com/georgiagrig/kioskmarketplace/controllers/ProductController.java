package com.georgiagrig.kioskmarketplace.controllers;

import com.georgiagrig.kioskmarketplace.repositories.ProductRepository;
import com.georgiagrig.kioskmarketplace.requests.create.ProductCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.ProductPatchRequest;
import com.georgiagrig.kioskmarketplace.response.ProductResponse;
import com.georgiagrig.kioskmarketplace.services.ProductService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    // GET {server URI}/product
    @GetMapping(value = "")
    public Iterable<ProductResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return ProductResponse.map(productService.list());
        } else {
            return ProductResponse.map(productService.find(parameters));
        }
    }

    // CREATE {server URI}/product
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<ProductResponse> create(@Valid @RequestBody ProductCreateRequest request) {
        Integer id = productService.create(request);
        return find(id);
    }

    // GET {server URI}/product/1
    @GetMapping(value = "/{id}")
    public Iterable<ProductResponse> find(@PathVariable("id") Integer id) {
        return ProductResponse.map(productService.find(id));
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<ProductResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody ProductPatchRequest request) {
        productService.patch(id, request);
        return find(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}


