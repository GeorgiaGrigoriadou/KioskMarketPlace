package com.georgiagrig.kioskmarketplace.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(mappedBy = "basket")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "basket")
    private List<BasketHasProduct> basketHasProducts = new ArrayList<>();
}
