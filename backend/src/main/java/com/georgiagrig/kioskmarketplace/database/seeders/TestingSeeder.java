package com.georgiagrig.kioskmarketplace.database.seeders;

import com.georgiagrig.kioskmarketplace.database.seeders.entities.*;
import com.georgiagrig.kioskmarketplace.entities.QuizUserAnswer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestingSeeder implements BaseSeeder {

    @Autowired
    private SpecialOfferSeeder specialOfferSeeder;

    @Autowired
    private QuizSeeder quizSeeder;

    @Autowired
    private UserSeeder userSeeder;

    @Autowired
    private ProductSeeder productSeeder;

    @Autowired
    private CustomerOrderSeeder customerOrderSeeder;

    @Autowired
    private OrderHasProductSeeder orderHasProductSeeder;

    @Autowired
    private QuizParticipationSeeder quizParticipationSeeder;

    @Autowired
    private QuizUserAnswerSeeder quizUserAnswerSeeder;

    @Autowired
    private BasketHasProductSeeder basketHasProductSeeder;




    public void seed() {
        log.info("Testing seeder started ...");

        userSeeder.seed();
        productSeeder.seed();
        customerOrderSeeder.seed();
        orderHasProductSeeder.seed();
        quizSeeder.seed();
        quizParticipationSeeder.seed();
        quizUserAnswerSeeder.seed();
        basketHasProductSeeder.seed();

        // TODO: call all other seeders
    }
}
