package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.CustomerOrder;
import com.georgiagrig.kioskmarketplace.entities.QuizParticipation;
import org.springframework.stereotype.Component;
import java.time.ZoneId;
import java.util.ArrayList;

import static com.georgiagrig.kioskmarketplace.database.seeders.BaseSeeder.faker;

@Component
public class QuizParticipationFactory extends BaseFactory<QuizParticipation> {
    public QuizParticipation make() {
        QuizParticipation item = new QuizParticipation();
        item.setCreatedAt(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return item;
    }
}
