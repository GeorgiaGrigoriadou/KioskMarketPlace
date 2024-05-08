package com.georgiagrig.kioskmarketplace.services;

import com.georgiagrig.kioskmarketplace.entities.OrderStatus;
import com.georgiagrig.kioskmarketplace.repositories.OrderStatusRepository;
import com.georgiagrig.kioskmarketplace.requests.create.OrderStatusCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.OrderStatusPatchRequest;
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
public class OrderStatusService {
    @Autowired
    OrderStatusRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<OrderStatus> list() {
        Iterable<OrderStatus> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<OrderStatus> find(Integer id) {
        OrderStatus result = repository.findByIdOrFail(id);

        ArrayList<OrderStatus> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(OrderStatusCreateRequest request) {
        OrderStatus entity = new OrderStatus();
        entity.setDescription(request.getDescription());
        entity.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        repository.save(entity);
        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, OrderStatusPatchRequest request) {
        OrderStatus entity = repository.findByIdOrFail(id);

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
        repository.save(entity);
    }


    public Iterable<OrderStatus> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderStatus> cq = cb.createQuery(OrderStatus.class); // select * from rate ..

        Root<OrderStatus> criteria = cq.from(OrderStatus.class);

        for (Map.Entry<String,String> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equalsIgnoreCase("description")) {
                Predicate constraint = cb.like(criteria.get("description"), "%" + value + "%");
                cq.where(constraint);
            }
        }

        TypedQuery<OrderStatus> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}

