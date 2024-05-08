package com.georgiagrig.kioskmarketplace.requests.patch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizPatchRequest {
    @Size(min = 1, message = "This field must have a minimum length of 1 character")
    private String title;

}
