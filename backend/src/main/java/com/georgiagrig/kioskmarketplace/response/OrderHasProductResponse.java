package com.georgiagrig.kioskmarketplace.response;
import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.entities.OrderHasProduct;
import com.georgiagrig.kioskmarketplace.entities.Product;
import com.georgiagrig.kioskmarketplace.entities.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderHasProductResponse {
    private Integer id;
    private Integer order_id;
    private String name;
    private Integer quantity;
    private ProductResponse product;

    public static OrderHasProductResponse map(OrderHasProduct entity) {
        OrderHasProductResponse r = new OrderHasProductResponse();
        r.setId(entity.getId());
        r.setOrder_id(entity.getOrder().getId());
        r.setName(entity.getProduct().getName());
        r.setQuantity(entity.getQuantity());
        r.setProduct(ProductResponse.map(entity.getProduct()));
        return r;
    }

    public static Iterable<OrderHasProductResponse> map(Iterable<OrderHasProduct> entities){
        ArrayList<OrderHasProductResponse> result = new ArrayList<>();
        for (OrderHasProduct entity : entities) {
            result.add(map(entity));
        }

        return result;
    }

}
