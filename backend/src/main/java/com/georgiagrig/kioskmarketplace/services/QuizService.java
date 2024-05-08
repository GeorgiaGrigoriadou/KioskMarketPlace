package com.georgiagrig.kioskmarketplace.services;

import com.georgiagrig.kioskmarketplace.entities.Quiz;
import com.georgiagrig.kioskmarketplace.repositories.QuizRepository;
import com.georgiagrig.kioskmarketplace.requests.create.QuizCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.QuizPatchRequest;
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
public class QuizService {
    @Autowired
    QuizRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<Quiz> list() {
        Iterable<Quiz> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<Quiz> find(Integer id) {
        Quiz result = repository.findByIdOrFail(id);

        ArrayList<Quiz> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(QuizCreateRequest request) {
        Quiz entity = new Quiz();
        entity.setTitle(request.getTitle());
        repository.save(entity);
        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, QuizPatchRequest request) {
        Quiz entity = repository.findByIdOrFail(id);

        if (request.getTitle() != null) {
            entity.setTitle(request.getTitle());
        }
        repository.save(entity);
    }


    public Iterable<Quiz> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Quiz> cq = cb.createQuery(Quiz.class); // select * from rate ..

        Root<Quiz> criteria = cq.from(Quiz.class);

        for (Map.Entry<String,String> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equalsIgnoreCase("title")) {
                Predicate constraint = cb.like(criteria.get("title"), "%" + value + "%");
                cq.where(constraint);
            }
        }

        TypedQuery<Quiz> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
