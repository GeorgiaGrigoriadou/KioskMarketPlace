package com.georgiagrig.kioskmarketplace.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description", length = 45)
    @NotNull
    private String description;

    @CreationTimestamp
    @Column(name = "created_at")
    @NotNull
    protected LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderStatus")
    private List<CustomerOrder> orders = new ArrayList<>();






}
