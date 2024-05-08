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
@Table(name = "product_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 45)
    @NotNull
    private String name;

    @CreatedDate
    @NotNull
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
    private List<Product> products = new ArrayList<>();
}

