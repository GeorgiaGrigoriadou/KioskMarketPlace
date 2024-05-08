
package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class QuizFactory extends BaseFactory<Quiz> {
    public Quiz make() {
        Quiz item = new Quiz();
        item.setTitle(faker.lorem().sentence());
        return item;
    }
}