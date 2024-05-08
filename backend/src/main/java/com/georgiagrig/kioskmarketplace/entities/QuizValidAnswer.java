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
@Table(name = "quiz_valid_answer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizValidAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "correct", columnDefinition = "TINYINT(1)")
    @NotNull
    private Boolean correct;

    @Column(name = "user_choice", length = 50)
    @NotNull
    private String user_choice;

    //
    // Relationships
    //
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private QuizQuestion question;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "answer")
    private List<QuizUserAnswer> answers = new ArrayList<>();

}
