package com.georgiagrig.kioskmarketplace.response;
import com.georgiagrig.kioskmarketplace.entities.*;
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
public class CustomerOrderResponse {
    private Integer id;
    private Double totalPrice;
    private OrderStatusResponse orderStatus;
    private LocalDateTime createdAt;
    private UserResponse customer;
    private Iterable<OrderHasProductResponse> details;

    public static CustomerOrderResponse map(CustomerOrder entity) {
        CustomerOrderResponse r = new CustomerOrderResponse();
        r.setId(entity.getId());
        r.setTotalPrice(entity.getTotal_price());
        r.setOrderStatus(OrderStatusResponse.map(entity.getOrderStatus()));
        r.setCustomer(UserResponse.map(entity.getUser()));
        r.setCreatedAt(entity.getCreatedAt());
        r.setDetails(OrderHasProductResponse.map(entity.getOrderHasProducts()));
        return r;
    }

    public static Iterable<CustomerOrderResponse> map(Iterable<CustomerOrder> entities) {
        ArrayList<CustomerOrderResponse> result = new ArrayList<>();

        for (CustomerOrder entity : entities) {
            result.add(map(entity));
        }
        return result;
    }
}
