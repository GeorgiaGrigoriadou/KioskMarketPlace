package com.georgiagrig.kioskmarketplace.database.seeders.entities;
import com.georgiagrig.kioskmarketplace.database.factories.QuizParticipationFactory;
import com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder;
import com.georgiagrig.kioskmarketplace.entities.QuizParticipation;
import com.georgiagrig.kioskmarketplace.repositories.QuizParticipationRepository;
import com.georgiagrig.kioskmarketplace.repositories.QuizRepository;
import com.georgiagrig.kioskmarketplace.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Slf4j
public class QuizParticipationSeeder implements BaseSeeder {

    @Autowired
    private QuizParticipationFactory factory;

    @Autowired
    private QuizParticipationRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    public void seed() {
        log.info("QuizParticipationSeeder seeder started ...");

        Iterable<QuizParticipation> data = factory.make(10);

        for (QuizParticipation quizParticipation : data) {
            quizParticipation.setQuiz(quizRepository.random());
            quizParticipation.setUser(userRepository.random());
            repository.save(quizParticipation);
        }


    }
}
