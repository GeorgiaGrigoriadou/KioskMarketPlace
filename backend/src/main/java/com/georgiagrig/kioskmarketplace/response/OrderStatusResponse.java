package com.georgiagrig.kioskmarketplace.response;

import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.entities.OrderStatus;
import com.georgiagrig.kioskmarketplace.entities.OrderStatus;
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
public class OrderStatusResponse {
    private Integer id;
    private String description;
    protected LocalDateTime createdAt;

    public static OrderStatusResponse map(OrderStatus entity) {
        OrderStatusResponse r = new OrderStatusResponse();
        r.setId(entity.getId());
        r.setDescription(entity.getDescription());
        r.setCreatedAt(entity.getCreatedAt());
        return r;
    }

    public static Iterable<OrderStatusResponse> map(Iterable<OrderStatus> entities) {
        ArrayList<OrderStatusResponse> result = new ArrayList<>();

        for (OrderStatus entity : entities) {
            result.add(map(entity));
        }

        return result;
    }
}
