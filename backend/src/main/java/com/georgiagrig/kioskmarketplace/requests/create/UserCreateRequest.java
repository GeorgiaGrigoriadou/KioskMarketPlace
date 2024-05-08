package com.georgiagrig.kioskmarketplace.requests.create;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateRequest {
    @NotBlank
    @Size(min = 3, message = "This field must have a minimum length of 1 character")
    private String username;

    @NotBlank
    @Size(min = 1, message = "This field must have a minimum length of 1 character")
    private String password;

    @NotBlank
    @Size(min = 1, message = "This field must have a minimum length of 1 character")
    private String name;

    @NotBlank
    @Size(min = 1, message = "This field must have a minimum length of 1 character")
    private String phone;

    @NotBlank
    @Email(message = "This field must have a minimum length of 1 character")
    private String email;

    @PositiveOrZero
    private Integer total_points;

    @Positive
    private Integer basket_id;

    @NotNull
    private Boolean is_administrator;

}

