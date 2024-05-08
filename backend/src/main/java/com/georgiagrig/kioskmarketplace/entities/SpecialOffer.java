package com.georgiagrig.kioskmarketplace.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "special_offer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpecialOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "discount")
    @NotNull
    private Double discount;

    @Column(name = "points")
    @NotNull
    private Integer points;

    @CreatedDate
    @NotNull
    @Column(name = "date_start")
    protected LocalDateTime dateStart;

    @CreatedDate
    @NotNull
    @Column(name = "date_end")
    protected LocalDateTime dateEnd;

    @CreatedDate
    @NotNull
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product product;

}
