
package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.QuizQuestion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class QuestionFactory extends BaseFactory<QuizQuestion> {

    public QuizQuestion make() {
        QuizQuestion item = new QuizQuestion();
        item.setText(faker.lorem().sentence());
        item.setPoints(faker.random().nextInt(10, 50));
        return item;
    }
}