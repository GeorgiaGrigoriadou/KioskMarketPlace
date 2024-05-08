package com.georgiagrig.kioskmarketplace.controllers;

import com.georgiagrig.kioskmarketplace.repositories.UserRepository;
import com.georgiagrig.kioskmarketplace.requests.create.UserCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.UserPatchRequest;
import com.georgiagrig.kioskmarketplace.response.UserResponse;
import com.georgiagrig.kioskmarketplace.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowirelist, findd
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // GET {server URI}/user
    @GetMapping(value = "")
    public Iterable<UserResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return UserResponse.map(userService.list());
        } else {
            return UserResponse.map(userService.find(parameters));
        }
    }

    // CREATE {server URI}/user
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<UserResponse> create(@Valid @RequestBody UserCreateRequest request) {
        Integer id = userService.create(request);
        return find(id);
    }

    // GET {server URI}/user/1
    @GetMapping(value = "/{id}")
    public Iterable<UserResponse> find(@PathVariable("id") Integer id) {
        return UserResponse.map(userService.find(id));
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<UserResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody UserPatchRequest request) {
        userService.patch(id, request);
        return find(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
