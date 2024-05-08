package com.georgiagrig.kioskmarketplace.controllers;

import com.georgiagrig.kioskmarketplace.repositories.QuizRepository;
import com.georgiagrig.kioskmarketplace.requests.create.QuizCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.QuizPatchRequest;
import com.georgiagrig.kioskmarketplace.response.QuizQuestionResponse;
import com.georgiagrig.kioskmarketplace.response.QuizResponse;
import com.georgiagrig.kioskmarketplace.services.QuizQuestionService;
import com.georgiagrig.kioskmarketplace.services.QuizService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizQuestionService questionService;

    // GET {server URI}/quiz
    @GetMapping(value = "")
    public Iterable<QuizResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return QuizResponse.map(quizService.list());
        } else {
            return QuizResponse.map(quizService.find(parameters));
        }
    }

    // CREATE {server URI}/quiz
    @PostMapping(value = "")
    @ResponseBody
    public Iterable<QuizResponse> create(@Valid @RequestBody QuizCreateRequest request) {
        Integer id = quizService.create(request);
        return find(id);
    }

    // GET {server URI}/quiz/1
    @GetMapping(value = "/{id}")
    public Iterable<QuizResponse> find(@PathVariable("id") Integer id) {
        return QuizResponse.map(quizService.find(id));
    }

    @PatchMapping(value = "/{id}")
    @ResponseBody
    public Iterable<QuizResponse> patch(@PathVariable("id") Integer id, @Valid @RequestBody QuizPatchRequest request) {
        quizService.patch(id, request);
        return find(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        quizService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    // GET {server URI}/quiz/1
    @GetMapping(value = "/{id}/questions")
    public Iterable<QuizQuestionResponse> findQuestionsByQuizId(@PathVariable("id") Integer id) {
        return QuizQuestionResponse.map(questionService.findByQuestionId(id));
    }
}
