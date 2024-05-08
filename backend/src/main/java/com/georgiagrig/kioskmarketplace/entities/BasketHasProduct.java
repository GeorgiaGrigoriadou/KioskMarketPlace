package com.georgiagrig.kioskmarketplace.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;


@Entity
@Table(name = "basket_has_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BasketHasProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Basket basket;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Product product;


}
