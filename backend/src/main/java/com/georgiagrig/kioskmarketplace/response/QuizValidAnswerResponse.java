package com.georgiagrig.kioskmarketplace.response;

import com.georgiagrig.kioskmarketplace.entities.QuizUserAnswer;
import com.georgiagrig.kioskmarketplace.entities.QuizValidAnswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizValidAnswerResponse {
    private Integer id;
    private Boolean correct;
    private String user_choice;

    public static QuizValidAnswerResponse map(QuizValidAnswer entity) {
        QuizValidAnswerResponse r = new QuizValidAnswerResponse();
        r.setId(entity.getId());
        r.setCorrect(entity.getCorrect());
        r.setUser_choice(entity.getUser_choice());

        return r;
    }

    public static Iterable<QuizValidAnswerResponse> map(Iterable<QuizValidAnswer> entities) {
        ArrayList<QuizValidAnswerResponse> result = new ArrayList<>();

        for (QuizValidAnswer entity : entities) {
            result.add(map(entity));
        }

        return result;
    }
}
