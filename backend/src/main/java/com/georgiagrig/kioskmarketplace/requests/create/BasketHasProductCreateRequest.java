package com.georgiagrig.kioskmarketplace.requests.create;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BasketHasProductCreateRequest {
    @Positive
    private Integer basket_id;

    @Positive
    private Integer product_id;

    @Positive
    private Integer quantity;
}
