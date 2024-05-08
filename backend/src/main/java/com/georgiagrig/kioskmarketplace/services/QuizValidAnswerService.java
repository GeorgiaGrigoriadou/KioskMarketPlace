package com.georgiagrig.kioskmarketplace.services;
import com.georgiagrig.kioskmarketplace.entities.QuizQuestion;
import com.georgiagrig.kioskmarketplace.entities.QuizValidAnswer;
import com.georgiagrig.kioskmarketplace.repositories.QuizQuestionRepository;
import com.georgiagrig.kioskmarketplace.repositories.QuizValidAnswerRepository;
import com.georgiagrig.kioskmarketplace.requests.create.QuizValidAnswerCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.QuizValidAnswerPatchRequest;
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
public class QuizValidAnswerService {
    @Autowired
    QuizValidAnswerRepository repository;

    @Autowired
    QuizQuestionRepository quizQuestionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<QuizValidAnswer> list() {
        Iterable<QuizValidAnswer> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<QuizValidAnswer> find(Integer id) {
        QuizValidAnswer result = repository.findByIdOrFail(id);

        ArrayList<QuizValidAnswer> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(QuizValidAnswerCreateRequest request) {
        QuizValidAnswer entity = new QuizValidAnswer();
        QuizQuestion question = quizQuestionRepository.findByIdOrFail(request.getQuestion_id());
        entity.setQuestion(question);
        entity.setCorrect(request.getCorrect());
        entity.setUser_choice(request.getUser_choice());
        repository.save(entity);
        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, QuizValidAnswerPatchRequest request) {
        QuizValidAnswer entity = repository.findByIdOrFail(id);

        if (request.getCorrect() != null) {
            entity.setCorrect(request.getCorrect());
        }

        if (request.getUser_choice() != null) {
            entity.setUser_choice(request.getUser_choice());
        }
        repository.save(entity);
    }


    public Iterable<QuizValidAnswer> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuizValidAnswer> cq = cb.createQuery(QuizValidAnswer.class); // select * from rate ..

        Root<QuizValidAnswer> criteria = cq.from(QuizValidAnswer.class);

        int predicates = 0;

        for (String field : new String[]{"correct", "user_choice"}) {
            if (parameters.containsKey(field)) {
                Predicate constraint = cb.like(criteria.get(field), "%" + parameters.get(field) + "%");
                cq.where(constraint);
                predicates++;
            }
        }

        TypedQuery<QuizValidAnswer> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}


