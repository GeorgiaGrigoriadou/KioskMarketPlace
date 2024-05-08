package com.georgiagrig.kioskmarketplace.controllers;
import com.georgiagrig.kioskmarketplace.repositories.MeasurementUnitRepository;
import com.georgiagrig.kioskmarketplace.repositories.ProductCategoryRepository;
import com.georgiagrig.kioskmarketplace.requests.create.ProductCategoryCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.ProductCategoryPatchRequest;
import com.georgiagrig.kioskmarketplace.response.ProductCategoryResponse;
import com.georgiagrig.kioskmarketplace.services.ProductCategoryService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product_category")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    // GET {server URI}/product_category
    @GetMapping(value = "")
    public Iterable<ProductCategoryResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return ProductCategoryResponse.map(productCategoryService.list());
        } else {
            return ProductCategoryResponse.map(productCategoryService.find(parameters));
        }
    }

    // CREATE {server URI}/product_category
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<ProductCategoryResponse> create(@Valid @RequestBody ProductCategoryCreateRequest request) {
        Integer id = productCategoryService.create(request);
        return find(id);
    }

    // GET {server URI}/product_category/1
    @GetMapping(value = "/{id}")
    public Iterable<ProductCategoryResponse> find(@PathVariable("id") Integer id) {
        return ProductCategoryResponse.map(productCategoryService.find(id));
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<ProductCategoryResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody ProductCategoryPatchRequest request) {
        productCategoryService.patch(id, request);
        return find(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        productCategoryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
