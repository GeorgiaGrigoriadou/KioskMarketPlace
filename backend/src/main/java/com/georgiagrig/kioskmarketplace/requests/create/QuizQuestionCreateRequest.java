package com.georgiagrig.kioskmarketplace.requests.create;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizQuestionCreateRequest {
    @Positive
    private Integer quiz_id;

    @Size(min = 1, message = "This field must have a minimum length of 1 character")
    private String text;

    @PositiveOrZero
    private Integer point;
}
