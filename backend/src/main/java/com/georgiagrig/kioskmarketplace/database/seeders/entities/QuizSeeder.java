package com.georgiagrig.kioskmarketplace.database.seeders.entities;

import com.georgiagrig.kioskmarketplace.database.factories.QuizValidAsnwersFactory;
import com.georgiagrig.kioskmarketplace.database.factories.QuestionFactory;
import com.georgiagrig.kioskmarketplace.database.factories.QuizFactory;
import com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder;
import com.georgiagrig.kioskmarketplace.entities.QuizValidAnswer;
import com.georgiagrig.kioskmarketplace.entities.QuizQuestion;
import com.georgiagrig.kioskmarketplace.entities.Quiz;
import com.georgiagrig.kioskmarketplace.repositories.QuizValidAnswerRepository;
import com.georgiagrig.kioskmarketplace.repositories.QuizQuestionRepository;
import com.georgiagrig.kioskmarketplace.repositories.QuizRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QuizSeeder implements BaseSeeder{
    @Autowired
    private QuizFactory factory;

    @Autowired
    private QuestionFactory questionFactory;

    @Autowired
    private QuizValidAsnwersFactory answerFactory;

    @Autowired
    private QuizRepository repository;

    @Autowired
    private QuizQuestionRepository questionRepository;

    @Autowired
    private QuizValidAnswerRepository answerRepository;



    public void seed() {
        log.info("QuizSeeder seeder started ...");

        Iterable<Quiz> data = factory.make(10);


        for (Quiz quiz : data) {
            int n = faker.random().nextInt(4,10);

            repository.save(quiz);

            Iterable<QuizQuestion> questions = questionFactory.make(n);

            for (QuizQuestion q : questions) {
                q.setQuiz(quiz);
                questionRepository.save(q);

                Iterable<QuizValidAnswer> answers = answerFactory.make(n);

                for(QuizValidAnswer a : answers){
                    a.setQuestion(q); // Set the relationship to the question
                    answerRepository.save(a); // Save the answer
                    q.getQuiz_valid_answers().add(a); // Add the answer to the question
                }
                quiz.getQuestions().add(q);

            }

        }
    }
}
