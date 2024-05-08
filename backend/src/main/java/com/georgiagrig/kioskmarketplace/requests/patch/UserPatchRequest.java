package com.georgiagrig.kioskmarketplace.requests.patch;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPatchRequest {
    @Size(min = 3, message = "This field must have a minimum length of 1 character")
    private String username;

    @Size(min = 1, message = "This field must have a minimum length of 1 character")
    private String password;

    @Size(min = 1, message = "This field must have a minimum length of 1 character")
    private String name;

    @Size(min = 1, message = "This field must have a minimum length of 1 character")
    private String phone;

    @Email
    private String email;

    @PositiveOrZero
    private Integer total_points;

    @Positive
    private Integer basket_id;

    private Boolean is_administrator;

}

