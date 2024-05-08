package com.georgiagrig.kioskmarketplace.response;

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
public class SpecialOfferResponse {
    private Integer id;
    private Integer product_id;
    private Double discount;
    private Integer points;
    protected LocalDateTime createdAt;
    protected LocalDateTime offerDateStart;
    protected LocalDateTime offerDateEnd;

    public static SpecialOfferResponse map(SpecialOffer entity) {
        SpecialOfferResponse r = new SpecialOfferResponse();
        r.setId(entity.getId());
        r.setProduct_id(entity.getProduct().getId());
        r.setDiscount(entity.getDiscount());
        r.setPoints(entity.getPoints());
        r.setOfferDateStart(entity.getDateStart());
        r.setOfferDateEnd(entity.getDateEnd());
        r.setCreatedAt(entity.getCreatedAt());
        return r;
    }
    public static Iterable<SpecialOfferResponse> map(Iterable<SpecialOffer> entities) {
        ArrayList<SpecialOfferResponse> result = new ArrayList<>();

        for (SpecialOffer entity : entities) {
            result.add(map(entity));
        }
        return result;
    }
}
