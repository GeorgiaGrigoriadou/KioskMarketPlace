package com.georgiagrig.kioskmarketplace.services;

import com.georgiagrig.kioskmarketplace.entities.Basket;
import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.entities.User;
import com.georgiagrig.kioskmarketplace.repositories.BasketRepository;
import com.georgiagrig.kioskmarketplace.repositories.UserRepository;
import com.georgiagrig.kioskmarketplace.requests.create.UserCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.UserPatchRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    BasketRepository basketRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<User> list() {
        Iterable<User> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<User> find(Integer id) {
        User result = repository.findByIdOrFail(id);

        ArrayList<User> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(UserCreateRequest request) {
        User entity = new User();
        entity.setUsername(request.getUsername());
        entity.setPassword(request.getPassword());
        entity.setName(request.getName());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setTotal_points(request.getTotal_points());
        entity.setIs_administrator(request.getIs_administrator());
        entity.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        Basket b = new Basket();

        basketRepository.save(b);

        entity.setBasket(b);
        repository.save(entity);

        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, UserPatchRequest request) {
        User entity = repository.findByIdOrFail(id);

        if (request.getUsername() != null) {
            entity.setUsername(request.getUsername());
        }

        if (request.getPassword() != null) {
            entity.setPassword(request.getPassword());
        }

        if (request.getName() != null) {
            entity.setName(request.getName());
        }

        if (request.getPhone() != null) {
            entity.setPhone(request.getPhone());
        }

        if (request.getEmail() != null) {
            entity.setEmail(request.getEmail());
        }

        if (request.getTotal_points() != null) {
            entity.setTotal_points(request.getTotal_points());
        }

        if (request.getIs_administrator() != null) {
            entity.setIs_administrator(request.getIs_administrator());
        }
        if (request.getBasket_id() != null) {
            Basket b =basketRepository.findByIdOrFail(request.getBasket_id());
            entity.setBasket(b);
        }

        repository.save(entity);
    }

    public Iterable<User> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class); // select * from rate ..

        Root<User> criteria = cq.from(User.class);

        int predicates = 0;
        for (String field : new String[]{"username", "name", "phone", "email"}) {
            if (parameters.containsKey(field)) {
                Predicate constraint = cb.like(criteria.get(field), "%" + parameters.get(field) + "%");
                cq.where(constraint);
                predicates++;
            }
        }

        for (String field : new String[]{"total_points", "is_administrator"}) {
            if (parameters.containsKey(field)) {
                String value = parameters.get(field);
                Integer integer = Integer.parseInt(value);

                Predicate constraint = cb.equal(criteria.get(field), integer);
                cq.where(constraint);
                predicates++;
            }
        }

        if (predicates == 0) {
            throw new IllegalArgumentException("No predicates defined");
        }

        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
