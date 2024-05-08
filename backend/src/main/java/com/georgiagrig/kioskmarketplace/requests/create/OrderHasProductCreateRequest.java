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
public class OrderHasProductCreateRequest {
    @Positive
    private Integer quantity;

    @Positive
    private Integer order_id;

    @Positive
    private Integer product_id;
}
