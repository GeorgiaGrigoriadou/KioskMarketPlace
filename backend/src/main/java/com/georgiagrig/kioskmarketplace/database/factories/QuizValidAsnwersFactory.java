package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.QuizValidAnswer;
import org.springframework.stereotype.Component;

@Component
public class QuizValidAsnwersFactory extends BaseFactory<QuizValidAnswer> {
    public QuizValidAnswer make() {
        QuizValidAnswer item = new QuizValidAnswer();
        item.setUser_choice(faker.lorem().sentence());
        item.setCorrect(faker.bool().bool());
        return item;
    }
}
