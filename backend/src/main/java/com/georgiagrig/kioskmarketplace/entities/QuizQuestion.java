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
@Table(name = "quiz_question")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizQuestion {
    //
    // Properties
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text", length = 200)
    @NotNull
    private String text;

    @Column(name = "points")
    @NotNull
    private Integer points;



    //
    // Relationships
    //

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    private List<QuizValidAnswer> quiz_valid_answers = new ArrayList<>();

    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Quiz quiz;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quizQuestion")
    private List<QuizUserAnswer> answers = new ArrayList<>();




}
