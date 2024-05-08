package com.georgiagrig.kioskmarketplace.response;

import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
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
public class MeasurementUnitResponse {
    private Integer id;
    private String code;
    private LocalDateTime createdAt;

    public static MeasurementUnitResponse map(MeasurementUnit entity) {
        MeasurementUnitResponse r = new MeasurementUnitResponse();
        r.setId(entity.getId());
        r.setCode(entity.getCode());
        r.setCreatedAt(entity.getCreatedAt());
        return r;
    }


    public static Iterable<MeasurementUnitResponse> map(Iterable<MeasurementUnit> entities) {
        ArrayList<MeasurementUnitResponse> result = new ArrayList<>();

        for (MeasurementUnit entity : entities) {
            result.add(map(entity));
        }

        return result;
    }
}
