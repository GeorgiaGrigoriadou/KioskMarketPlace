package com.georgiagrig.kioskmarketplace.controllers;

import com.georgiagrig.kioskmarketplace.repositories.SpecialOfferRepository;
import com.georgiagrig.kioskmarketplace.requests.create.SpecialOfferCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.SpecialOfferPatchRequest;
import com.georgiagrig.kioskmarketplace.response.SpecialOfferResponse;
import com.georgiagrig.kioskmarketplace.services.SpecialOfferService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/specialOffer")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferService specialOfferService;

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    // GET {server URI}/specialOffer
    @GetMapping(value = "")
    public Iterable<SpecialOfferResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return SpecialOfferResponse.map(specialOfferService.list());
        } else {
            return SpecialOfferResponse.map(specialOfferService.find(parameters));
        }
    }

    // CREATE {server URI}/specialOffer
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<SpecialOfferResponse> create(@Valid @RequestBody SpecialOfferCreateRequest request) {
        Integer id = specialOfferService.create(request);
        return find(id);
    }

    // GET {server URI}/specialOffer/1
    @GetMapping(value = "/{id}")
    public Iterable<SpecialOfferResponse> find(@PathVariable("id") Integer id) {
        return SpecialOfferResponse.map(specialOfferService.find(id));
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<SpecialOfferResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody SpecialOfferPatchRequest request) {
        specialOfferService.patch(id, request);
        return find(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        specialOfferService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}


