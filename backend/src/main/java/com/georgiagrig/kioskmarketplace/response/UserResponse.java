package com.georgiagrig.kioskmarketplace.response;

import com.georgiagrig.kioskmarketplace.entities.Basket;
import com.georgiagrig.kioskmarketplace.entities.SpecialOffer;
import com.georgiagrig.kioskmarketplace.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String username;
    // private String password;
    private String name;
    private String phone;
    private String email;
    private Integer total_points;
    private Boolean is_administrator;
    protected LocalDateTime createdAt;

    public static UserResponse map(User entity) {
        UserResponse r = new UserResponse();
        r.setId(entity.getId());
        r.setUsername(entity.getUsername());
        r.setName(entity.getName());
        r.setPhone(entity.getPhone());
        r.setEmail(entity.getEmail());
        r.setTotal_points(entity.getTotal_points());
        r.setIs_administrator(entity.getIs_administrator());
        r.setCreatedAt(entity.getCreatedAt());
        return r;
    }

    public static Iterable<UserResponse> map(Iterable<User> entities) {
        ArrayList<UserResponse> result = new ArrayList<>();

        for (User entity : entities) {
            result.add(map(entity));
        }
        return result;
    }
}
