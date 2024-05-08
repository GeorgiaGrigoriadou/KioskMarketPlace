package com.georgiagrig.kioskmarketplace.controllers;

import com.georgiagrig.kioskmarketplace.repositories.MeasurementUnitRepository;
import com.georgiagrig.kioskmarketplace.requests.create.MeasurementUnitCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.MeasurementUnitPatchRequest;
import com.georgiagrig.kioskmarketplace.response.MeasurementUnitResponse;
import com.georgiagrig.kioskmarketplace.services.MeasurementUnitService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/measurement_unit")
public class MeasurementUnitController {

    @Autowired
    private MeasurementUnitService measurementUnitService;

    @Autowired
    private MeasurementUnitRepository measurementUnitRepository;

    // GET {server URI}/measurement_unit
    @GetMapping(value = "")
    public Iterable<MeasurementUnitResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return MeasurementUnitResponse.map(measurementUnitService.list());
        } else {
            return MeasurementUnitResponse.map(measurementUnitService.find(parameters));
        }
    }

    // CREATE {server URI}/measurement_unit
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<MeasurementUnitResponse> create(@Valid @RequestBody MeasurementUnitCreateRequest request) {
        Integer id = measurementUnitService.create(request);
        return find(id);
    }

    // GET {server URI}/measurement_unit/1
    @GetMapping(value = "/{id}")
    public Iterable<MeasurementUnitResponse> find(@PathVariable("id") Integer id) {
        return MeasurementUnitResponse.map(measurementUnitService.find(id));
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<MeasurementUnitResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody MeasurementUnitPatchRequest request) {
        measurementUnitService.patch(id, request);
        return find(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        measurementUnitService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
