package com.georgiagrig.kioskmarketplace.requests.patch;

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
public class SpecialOfferPatchRequest {
    @PositiveOrZero
    private Double discount;

    @PositiveOrZero
    private Integer point;

    @Positive
    private Integer productId;

    @NotNull
    private LocalDateTime offerDateStart;

    @NotNull
    private LocalDateTime offerDateEnd;
}
