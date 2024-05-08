package com.georgiagrig.kioskmarketplace.response;
import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.entities.QuizParticipation;
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
public class QuizParticipationResponse {
    private Integer id;
    private Integer user_id;
    private Integer quiz_id;


    public static QuizParticipationResponse map(QuizParticipation entity) {
        QuizParticipationResponse r = new QuizParticipationResponse();
        r.setId(entity.getId());
        r.setUser_id(entity.getUser().getId());
        r.setQuiz_id(entity.getQuiz().getId());
        return r;
    }

    public static Iterable<QuizParticipationResponse> map(Iterable<QuizParticipation> entities) {
        ArrayList<QuizParticipationResponse> result = new ArrayList<>();

        for (QuizParticipation entity : entities) {
            result.add(map(entity));
        }

        return result;
    }
}
