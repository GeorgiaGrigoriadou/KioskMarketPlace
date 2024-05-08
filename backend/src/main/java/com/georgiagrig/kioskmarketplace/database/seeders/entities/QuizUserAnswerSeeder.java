package com.georgiagrig.kioskmarketplace.database.seeders.entities;

import com.georgiagrig.kioskmarketplace.database.factories.QuizUserAnswerFactory;
import com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder;
import com.georgiagrig.kioskmarketplace.entities.QuizUserAnswer;
import com.georgiagrig.kioskmarketplace.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QuizUserAnswerSeeder implements BaseSeeder {
    @Autowired
    private QuizUserAnswerRepository quizUserAnswerRepository;

    @Autowired
    private QuizUserAnswerFactory quizUserAnswerFactory;

    @Autowired
    private QuizParticipationRepository quizParticipationRepository;

    @Autowired
    private QuizQuestionRepository questionRepository;

    @Autowired
    private QuizValidAnswerRepository answerRepository;

    public void seed() {
        log.info("QuizUserAnswerSeeder seeder started ...");
        Iterable<QuizUserAnswer> data = quizUserAnswerFactory.make(10);

        for (QuizUserAnswer quizUserAnswer : data) {
            quizUserAnswer.setQuizParticipation(quizParticipationRepository.random());
            quizUserAnswer.setQuizQuestion(questionRepository.random());
            quizUserAnswer.setAnswer(answerRepository.random());
            quizUserAnswerRepository.save(quizUserAnswer);

        }




    }
}
