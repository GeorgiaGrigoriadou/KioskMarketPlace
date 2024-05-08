package com.georgiagrig.kioskmarketplace.database.factories;
import com.georgiagrig.kioskmarketplace.entities.OrderHasProduct;
import com.georgiagrig.kioskmarketplace.entities.QuizUserAnswer;
import org.springframework.stereotype.Component;

@Component
public class QuizUserAnswerFactory extends BaseFactory<QuizUserAnswer>{
    public QuizUserAnswer make() {
        QuizUserAnswer item = new QuizUserAnswer();
        return item;
    }
}
