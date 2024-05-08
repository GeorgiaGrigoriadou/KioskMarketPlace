package com.georgiagrig.kioskmarketplace.repositories;

import com.georgiagrig.kioskmarketplace.entities.OrderStatus;
import com.georgiagrig.kioskmarketplace.entities.QuizParticipation;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizParticipationRepository extends BaseRepository<QuizParticipation, Integer> {
}
