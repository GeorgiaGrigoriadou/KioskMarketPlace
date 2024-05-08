package com.georgiagrig.kioskmarketplace.response;

import com.georgiagrig.kioskmarketplace.entities.QuizParticipation;
import com.georgiagrig.kioskmarketplace.entities.QuizQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizQuestionResponse {
    private Integer id;
    private Integer quiz_id;
    private String text;
    private Integer points;

    private Iterable<QuizValidAnswerResponse> answers = new ArrayList<>();

    public static QuizQuestionResponse map(QuizQuestion entity) {
        QuizQuestionResponse r = new QuizQuestionResponse();
        r.id = entity.getId();
        r.setText(entity.getText());
        r.setPoints(entity.getPoints());
        r.setQuiz_id(entity.getQuiz().getId());
        r.answers = QuizValidAnswerResponse.map(entity.getQuiz_valid_answers());
        return r;
    }

    public static Iterable<QuizQuestionResponse> map(Iterable<QuizQuestion> entities) {
        ArrayList<QuizQuestionResponse> result = new ArrayList<>();
        for (QuizQuestion entity : entities) {
            result.add(map(entity));
        }

        return result;
    }
}
