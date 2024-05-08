package com.georgiagrig.kioskmarketplace.requests.create;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizValidAnswerCreateRequest {
    private Boolean correct;

    @Size(min = 1, message = "This field must have a minimum length of 1 character")
    private String user_choice;

    @Positive
    private Integer question_id;
}
