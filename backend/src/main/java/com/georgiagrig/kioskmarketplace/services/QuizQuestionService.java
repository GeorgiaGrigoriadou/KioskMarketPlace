package com.georgiagrig.kioskmarketplace.services;
import com.georgiagrig.kioskmarketplace.entities.Quiz;
import com.georgiagrig.kioskmarketplace.entities.QuizQuestion;
import com.georgiagrig.kioskmarketplace.repositories.QuizQuestionRepository;
import com.georgiagrig.kioskmarketplace.repositories.QuizRepository;
import com.georgiagrig.kioskmarketplace.requests.create.QuizQuestionCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.QuizQuestionPatchRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Map;

@Service
public class QuizQuestionService {
    @Autowired
    QuizQuestionRepository repository;

    @Autowired
    QuizRepository quizRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<QuizQuestion> list() {
        Iterable<QuizQuestion> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<QuizQuestion> find(Integer id) {
        QuizQuestion result = repository.findByIdOrFail(id);

        ArrayList<QuizQuestion> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(QuizQuestionCreateRequest request) {
        Quiz quiz = quizRepository.findByIdOrFail(request.getQuiz_id());

        QuizQuestion entity = new QuizQuestion();
        entity.setQuiz(quiz);
        entity.setText(request.getText());
        entity.setPoints(request.getPoint());
        repository.save(entity);
        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, QuizQuestionPatchRequest request) {
        QuizQuestion entity = repository.findByIdOrFail(id);

        if (request.getText() != null) {
            entity.setText(request.getText());
        }

        if (request.getPoint() != null) {
            entity.setPoints(request.getPoint());
        }
        repository.save(entity);
    }


    public Iterable<QuizQuestion> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuizQuestion> cq = cb.createQuery(QuizQuestion.class); // select * from rate ..

        Root<QuizQuestion> criteria = cq.from(QuizQuestion.class);

        int predicates = 0;
        for (String field : new String[]{"text"}) {
            if (parameters.containsKey(field)) {
                Predicate constraint = cb.like(criteria.get(field), "%" + parameters.get(field) + "%");
                cq.where(constraint);
                predicates++;
            }
        }
        for (String field : new String[]{"points"}) {
            if (parameters.containsKey(field)) {
                String value = parameters.get(field);
                Integer integer = Integer.parseInt(value);

                Predicate constraint = cb.equal(criteria.get(field), integer);
                cq.where(constraint);
                predicates++;
            }
        }

        for (String field : new String[]{"quiz_id"}) {
            if (parameters.containsKey(field)) {
                String value = parameters.get(field);
                Integer integer = Integer.parseInt(value);

                Join<Object, Object> joinQuizes = criteria.join("quiz", JoinType.INNER);

                Predicate constraint = cb.equal(joinQuizes.get("id"), integer);
                cq.where(constraint);
                predicates++;
            }
        }

        TypedQuery<QuizQuestion> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public Iterable<QuizQuestion> findByQuestionId(Integer id) {
        Quiz quiz = quizRepository.findByIdOrFail(id);
        return quiz.getQuestions();
    }
}

