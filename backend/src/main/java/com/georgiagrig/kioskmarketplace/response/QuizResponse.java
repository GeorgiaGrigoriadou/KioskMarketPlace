package com.georgiagrig.kioskmarketplace.response;

import com.georgiagrig.kioskmarketplace.entities.Quiz;
import com.georgiagrig.kioskmarketplace.entities.QuizQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizResponse {
    private Integer id;
    private String title;
    private Integer totalQuestions;
    private Integer totalPoints;

    private Iterable<QuizQuestionResponse> questions = new ArrayList<>();

    public static QuizResponse map(Quiz entity) {
        QuizResponse r = new QuizResponse();
        r.setId(entity.getId());
        r.setTitle(entity.getTitle());
        r.setTotalQuestions(entity.getQuestions().size());

        int sum = 0;

        for (QuizQuestion q : entity.getQuestions()) {
            sum += q.getPoints();
        }
        r.setTotalPoints(sum);
        r.questions = QuizQuestionResponse.map(entity.getQuestions());
        return r;
    }

    public static Iterable<QuizResponse> map(Iterable<Quiz> entities) {
        ArrayList<QuizResponse> result = new ArrayList<>();

        for (Quiz entity : entities) {
            result.add(map(entity));
        }

        return result;
    }
}
