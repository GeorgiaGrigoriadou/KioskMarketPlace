package com.georgiagrig.kioskmarketplace.requests.create;

import jakarta.validation.constraints.Positive;
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
public class ProductCreateRequest {

    @Size(min = 1, message = "This field must have a minimum length of 1 character")
    private String name;

    @Size(min = 1, message = "This field must have a minimum length of 1 character")
    private String description;

    @PositiveOrZero
    private Double price;

    @PositiveOrZero
    private Double retail_cost;

    @Positive
    private Integer measurement_unit_id;

    @Positive
    private Integer product_category_id;
}
