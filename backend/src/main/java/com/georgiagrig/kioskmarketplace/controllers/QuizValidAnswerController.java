package com.georgiagrig.kioskmarketplace.controllers;

import com.georgiagrig.kioskmarketplace.repositories.QuizValidAnswerRepository;
import com.georgiagrig.kioskmarketplace.requests.create.QuizValidAnswerCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.QuizValidAnswerPatchRequest;
import com.georgiagrig.kioskmarketplace.response.QuizValidAnswerResponse;
import com.georgiagrig.kioskmarketplace.services.QuizValidAnswerService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/quiz_valid_answer")
public class QuizValidAnswerController {

    @Autowired
    private QuizValidAnswerService answerService;

    @Autowired
    private QuizValidAnswerRepository answerRepository;

    // GET {server URI}/answer
    @GetMapping(value = "")
    public Iterable<QuizValidAnswerResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return QuizValidAnswerResponse.map(answerService.list());
        } else {
            return QuizValidAnswerResponse.map(answerService.find(parameters));
        }
    }

    // CREATE {server URI}/answer
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<QuizValidAnswerResponse> create(@Valid @RequestBody QuizValidAnswerCreateRequest request) {
        Integer id = answerService.create(request);
        return find(id);
    }

    // GET {server URI}/answer/1
    @GetMapping(value = "/{id}")
    public Iterable<QuizValidAnswerResponse> find(@PathVariable("id") Integer id) {
        return QuizValidAnswerResponse.map(answerService.find(id));
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<QuizValidAnswerResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody QuizValidAnswerPatchRequest request) {
        answerService.patch(id, request);
        return find(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        answerService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

