package com.georgiagrig.kioskmarketplace.response;

import com.georgiagrig.kioskmarketplace.entities.Product;

import com.georgiagrig.kioskmarketplace.entities.SpecialOffer;
import com.georgiagrig.kioskmarketplace.entities.User;
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
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Double retail_cost;
    protected LocalDateTime createdAt;

    private MeasurementUnitResponse measurementUnit;
    private ProductCategoryResponse productCategory;
    private Iterable<SpecialOfferResponse> specialOffers = new ArrayList<>();

    public static ProductResponse map(Product entity) {
        ProductResponse r = new ProductResponse();
        r.setId(entity.getId());
        r.setName(entity.getName());
        r.setDescription(entity.getDescription());
        r.setPrice(entity.getPrice());
        r.setRetail_cost(entity.getRetail_cost());
        r.setCreatedAt(entity.getCreatedAt());
        r.setMeasurementUnit(MeasurementUnitResponse.map(entity.getMeasurementUnit()));
        r.setProductCategory(ProductCategoryResponse.map(entity.getProductCategory()));
        r.setSpecialOffers(SpecialOfferResponse.map(entity.getSpecialOffers()));
        return r;
    }


    public static Iterable<ProductResponse> map(Iterable<Product> entities) {
        ArrayList<ProductResponse> result = new ArrayList<>();

        for (Product entity : entities) {
            result.add(map(entity));
        }
        return result;
    }
}
