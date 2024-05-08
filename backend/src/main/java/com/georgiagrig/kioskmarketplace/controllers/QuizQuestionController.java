package com.georgiagrig.kioskmarketplace.controllers;
import com.georgiagrig.kioskmarketplace.repositories.QuizQuestionRepository;
import com.georgiagrig.kioskmarketplace.requests.create.QuizQuestionCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.QuizQuestionPatchRequest;
import com.georgiagrig.kioskmarketplace.response.QuizQuestionResponse;
import com.georgiagrig.kioskmarketplace.services.QuizQuestionService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuizQuestionController {

    @Autowired
    private QuizQuestionService questionService;

    @Autowired
    private QuizQuestionRepository questionRepository;

    // GET {server URI}/question
    @GetMapping(value = "")
    public Iterable<QuizQuestionResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return QuizQuestionResponse.map(questionService.list());
        } else {
            return QuizQuestionResponse.map(questionService.find(parameters));
        }
    }

    // CREATE {server URI}/question
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<QuizQuestionResponse> create(@Valid @RequestBody QuizQuestionCreateRequest request) {
        Integer id = questionService.create(request);
        return find(id);
    }

    // GET {server URI}/question/1
    @GetMapping(value = "/{id}")
    public Iterable<QuizQuestionResponse> find(@PathVariable("id") Integer id) {
        return QuizQuestionResponse.map(questionService.find(id));
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<QuizQuestionResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody QuizQuestionPatchRequest request) {
        questionService.patch(id, request);
        return find(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        questionService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
