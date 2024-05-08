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
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 45)
    @NotNull
    private String name;

    @Column(name = "description", length = 200)
    @NotNull
    private String description;

    @Column(name = "price")
    @NotNull
    private Double price;

    @Column(name = "retail_cost")
    @NotNull
    private Double retail_cost;

    @CreatedDate
    @NotNull
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @JoinColumn(name = "measurement_unit_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private MeasurementUnit measurementUnit;

    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private ProductCategory productCategory;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<SpecialOffer> specialOffers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderHasProduct> orderHasProducts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<BasketHasProduct> basketHasProduct = new ArrayList<>();
}
