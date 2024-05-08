package com.georgiagrig.kioskmarketplace.entities;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quiz_user_answer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizUserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "quiz_participation_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private QuizParticipation quizParticipation;

    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private QuizQuestion quizQuestion;

    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private QuizValidAnswer answer;
}
