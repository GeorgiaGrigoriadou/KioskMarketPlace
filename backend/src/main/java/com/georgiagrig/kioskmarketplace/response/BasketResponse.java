package com.georgiagrig.kioskmarketplace.response;

import com.georgiagrig.kioskmarketplace.entities.Basket;
import com.georgiagrig.kioskmarketplace.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BasketResponse {
    private Integer id;
    private UserResponse owner;
    private Iterable<BasketHasProductResponse> products;

    public static BasketResponse map(Basket entity) {
        BasketResponse r = new BasketResponse();
        r.setOwner(UserResponse.map(entity.getUser()));
        r.setId(entity.getId());
        r.setProducts(BasketHasProductResponse.map(entity.getBasketHasProducts()));
        return r;
    }

    public static Iterable<BasketResponse> map(Iterable<Basket> entities) {
        ArrayList<BasketResponse> result = new ArrayList<>();

        for (Basket entity : entities) {
            result.add(map(entity));
        }
        return result;
    }
}
