package com.georgiagrig.kioskmarketplace.services;
import com.georgiagrig.kioskmarketplace.entities.QuizUserAnswer;
import com.georgiagrig.kioskmarketplace.repositories.QuizUserAnswerRepository;
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
public class QuizUserAnswerService {
    @Autowired
    QuizUserAnswerRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<QuizUserAnswer> list() {
        Iterable<QuizUserAnswer> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<QuizUserAnswer> find(Integer id) {
        QuizUserAnswer result = repository.findByIdOrFail(id);

        ArrayList<QuizUserAnswer> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public Iterable<QuizUserAnswer> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuizUserAnswer> cq = cb.createQuery(QuizUserAnswer.class); // select * from rate ..

        Root<QuizUserAnswer> criteria = cq.from(QuizUserAnswer.class);

        for (String field : new String[]{"quiz_participation_id", "question_id", "answer_id"}) {
            if (parameters.containsKey(field)) {
                String value = parameters.get(field);
                Integer integer = Integer.parseInt(value);

                Predicate constraint = cb.equal(criteria.get(field), integer);
                cq.where(constraint);
            }
        }

        TypedQuery<QuizUserAnswer> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
