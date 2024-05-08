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
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", length = 45)
    @NotNull
    private String username;

    @Column(name = "password", length = 45)
    @NotNull
    private String password;

    @Column(name = "name", length = 45)
    @NotNull
    private String name;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 200)
    @NotNull
    private String email;

    @Column(name = "total_points")
    @NotNull
    private Integer total_points;

    @Column(name = "is_administrator", columnDefinition = "TINYINT(1)")
    @NotNull
    private Boolean is_administrator;

    @CreatedDate
    @NotNull
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<CustomerOrder> orders = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<QuizParticipation> quiz = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;


}
