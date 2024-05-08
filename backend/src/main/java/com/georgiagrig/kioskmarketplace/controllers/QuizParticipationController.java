package com.georgiagrig.kioskmarketplace.controllers;
import com.georgiagrig.kioskmarketplace.repositories.QuizParticipationRepository;
import com.georgiagrig.kioskmarketplace.response.QuizParticipationResponse;
import com.georgiagrig.kioskmarketplace.services.QuizParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/quiz_participation")
public class QuizParticipationController {
    @Autowired
    private QuizParticipationService quizParticipationService;

    @Autowired
    private QuizParticipationRepository quizParticipationRepository;

    // GET {server URI}/question
    @GetMapping(value = "")
    public Iterable<QuizParticipationResponse> list(@RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return QuizParticipationResponse.map(quizParticipationService.list());
        } else {
            return QuizParticipationResponse.map(quizParticipationService.find(parameters));
        }
    }

    @GetMapping(value = "/{id}")
    public Iterable<QuizParticipationResponse> find(@PathVariable("id") Integer id) {
        return QuizParticipationResponse.map(quizParticipationService.find(id));
    }
}
