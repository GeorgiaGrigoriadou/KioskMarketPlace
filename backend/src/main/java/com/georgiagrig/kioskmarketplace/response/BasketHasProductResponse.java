package com.georgiagrig.kioskmarketplace.response;

import com.georgiagrig.kioskmarketplace.entities.BasketHasProduct;
import com.georgiagrig.kioskmarketplace.entities.QuizUserAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BasketHasProductResponse {
    private Integer id;
    private Integer basket_id;
    private ProductResponse product;
    private Integer quantity;

    public static BasketHasProductResponse map(BasketHasProduct entity){
        BasketHasProductResponse r = new BasketHasProductResponse();
        r.setId(entity.getId());
        r.setBasket_id(entity.getBasket().getId());
        r.setProduct(ProductResponse.map(entity.getProduct()));
        r.setQuantity(entity.getQuantity());
        return r;
    }

    public static Iterable<BasketHasProductResponse> map(Iterable<BasketHasProduct> entities) {
        ArrayList<BasketHasProductResponse> result = new ArrayList<>();

        for (BasketHasProduct entity : entities) {
            result.add(map(entity));
        }

        return result;
    }
}
