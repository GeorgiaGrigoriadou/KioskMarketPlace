package com.georgiagrig.kioskmarketplace.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quiz {
    //
    // Properties
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 200)
    @NotNull
    private String title;


    //
    // Relationships
    //
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "quiz")
    private List<QuizQuestion> questions = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz")
    private List<QuizParticipation> quiz = new ArrayList<>();
}
