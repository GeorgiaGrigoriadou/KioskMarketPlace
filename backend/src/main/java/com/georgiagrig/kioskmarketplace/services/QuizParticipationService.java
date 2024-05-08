package com.georgiagrig.kioskmarketplace.services;
import com.georgiagrig.kioskmarketplace.entities.QuizParticipation;
import com.georgiagrig.kioskmarketplace.repositories.QuizParticipationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Map;

@Service
public class QuizParticipationService {
    @Autowired
    QuizParticipationRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<QuizParticipation> list() {
        Iterable<QuizParticipation> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<QuizParticipation> find(Integer id) {
        QuizParticipation result = repository.findByIdOrFail(id);

        ArrayList<QuizParticipation> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public Iterable<QuizParticipation> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuizParticipation> cq = cb.createQuery(QuizParticipation.class); // select * from rate ..

        Root<QuizParticipation> criteria = cq.from(QuizParticipation.class);

        int predicates = 0;

        for (String field : new String[]{"user_id", "quiz_id"}) {
            if (parameters.containsKey(field)) {
                String value = parameters.get(field);
                Integer integer = Integer.parseInt(value);

                Predicate constraint = cb.equal(criteria.get(field), integer);
                cq.where(constraint);
                predicates++;
            }
        }

        TypedQuery<QuizParticipation> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
