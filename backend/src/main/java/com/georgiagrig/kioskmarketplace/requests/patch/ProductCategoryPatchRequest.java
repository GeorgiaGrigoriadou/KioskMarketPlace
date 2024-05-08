package com.georgiagrig.kioskmarketplace.requests.patch;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCategoryPatchRequest {
    @NotBlank
    private String name;
}
