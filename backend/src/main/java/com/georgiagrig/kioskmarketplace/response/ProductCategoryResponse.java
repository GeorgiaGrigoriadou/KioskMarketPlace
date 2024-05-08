package com.georgiagrig.kioskmarketplace.response;

import com.georgiagrig.kioskmarketplace.entities.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCategoryResponse {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private Integer totalProducts = 0;

    public static ProductCategoryResponse map(ProductCategory entity){
        ProductCategoryResponse r = new ProductCategoryResponse();
        r.setId(entity.getId());
        r.setName(entity.getName());
        r.setCreatedAt(entity.getCreatedAt());
        r.setTotalProducts(entity.getProducts().size());
        return r;
    }
    public static Iterable<ProductCategoryResponse> map(Iterable<ProductCategory> entities){
        ArrayList<ProductCategoryResponse> result = new ArrayList<>();

        for (ProductCategory entity : entities) {
            result.add(map(entity));
        }

        return result;
    }
}
