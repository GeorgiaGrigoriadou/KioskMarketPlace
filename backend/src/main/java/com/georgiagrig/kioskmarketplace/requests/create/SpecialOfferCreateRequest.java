package com.georgiagrig.kioskmarketplace.requests.create;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpecialOfferCreateRequest {
    @PositiveOrZero
    private Double discount;

    @PositiveOrZero
    private Integer points;

    @Positive
    private Integer product_id;

    @NotNull
    private LocalDateTime offerDateStart;

    @NotNull
    private LocalDateTime offerDateEnd;

}
