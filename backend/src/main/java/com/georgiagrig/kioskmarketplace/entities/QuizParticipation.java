package com.georgiagrig.kioskmarketplace.entities;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "quiz_participation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @CreatedDate
    @NotNull
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Quiz quiz;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quizParticipation")
    private List<QuizUserAnswer> answers = new ArrayList<>();
}
