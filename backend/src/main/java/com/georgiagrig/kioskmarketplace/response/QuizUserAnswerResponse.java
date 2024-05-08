package com.georgiagrig.kioskmarketplace.response;
import com.georgiagrig.kioskmarketplace.entities.Quiz;
import com.georgiagrig.kioskmarketplace.entities.QuizQuestion;
import com.georgiagrig.kioskmarketplace.entities.QuizUserAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizUserAnswerResponse {
    private Integer id;
    private Integer quiz_participation_id;
    private Integer question_id;
    private Integer answer_id;

    public static QuizUserAnswerResponse map(QuizUserAnswer entity) {
        QuizUserAnswerResponse r = new QuizUserAnswerResponse();
        r.setId(entity.getId());
        r.setAnswer_id(entity.getAnswer().getId());
        r.setQuiz_participation_id((entity.getQuizParticipation().getId()));
        r.setQuestion_id(entity.getQuizQuestion().getId());
        return r;
    }

    public static Iterable<QuizUserAnswerResponse> map(Iterable<QuizUserAnswer> entities) {
        ArrayList<QuizUserAnswerResponse> result = new ArrayList<>();

        for (QuizUserAnswer entity : entities) {
            result.add(map(entity));
        }

        return result;
    }
}
