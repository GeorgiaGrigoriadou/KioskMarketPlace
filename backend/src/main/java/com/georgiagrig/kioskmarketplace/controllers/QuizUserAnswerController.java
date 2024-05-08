package com.georgiagrig.kioskmarketplace.controllers;

import com.georgiagrig.kioskmarketplace.repositories.QuizUserAnswerRepository;
import com.georgiagrig.kioskmarketplace.response.QuizUserAnswerResponse;
import com.georgiagrig.kioskmarketplace.services.QuizUserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/quiz_user_answer")
public class QuizUserAnswerController {
    @Autowired
    private QuizUserAnswerService questionService;

    @Autowired
    private QuizUserAnswerRepository quizUserAnswerRepository;
    
    // GET {server URI}/question
    @GetMapping(value = "")
    public Iterable<QuizUserAnswerResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return QuizUserAnswerResponse.map(questionService.list());
        } else {
            return QuizUserAnswerResponse.map(questionService.find(parameters));
        }
    }

    @GetMapping(value = "/{id}")
    public Iterable<QuizUserAnswerResponse> find(@PathVariable("id") Integer id) {
        return QuizUserAnswerResponse.map(questionService.find(id));
    }
}
