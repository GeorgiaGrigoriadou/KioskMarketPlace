package com.georgiagrig.kioskmarketplace.services;

import com.georgiagrig.kioskmarketplace.entities.*;
import com.georgiagrig.kioskmarketplace.repositories.BasketHasProductRepository;
import com.georgiagrig.kioskmarketplace.repositories.BasketRepository;
import com.georgiagrig.kioskmarketplace.repositories.ProductRepository;
import com.georgiagrig.kioskmarketplace.requests.create.BasketHasProductCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.create.QuizCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.BasketHasProductPatchRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.OrderHasProductPatchRequest;
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
public class BasketHasProductService {
    @Autowired
    BasketHasProductRepository repository;
    @Autowired
    BasketRepository basketRepository;

    @Autowired
    ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<BasketHasProduct> list() {
        Iterable<BasketHasProduct> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<BasketHasProduct> find(Integer id) {
        BasketHasProduct result = repository.findByIdOrFail(id);

        ArrayList<BasketHasProduct> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(BasketHasProductCreateRequest request) {
        Basket basket = basketRepository.findByIdOrFail(request.getBasket_id());
        Product product = productRepository.findByIdOrFail(request.getProduct_id());

        BasketHasProduct entity = new BasketHasProduct();
        entity.setBasket(basket);
        entity.setProduct(product);
        entity.setQuantity(request.getQuantity());
        repository.save(entity);
        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, BasketHasProductPatchRequest request) {
        BasketHasProduct entity = repository.findByIdOrFail(id);

        if (request.getQuantity() != null) {
            entity.setQuantity(request.getQuantity());
        }
        repository.save(entity);
    }

    public Iterable<BasketHasProduct> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BasketHasProduct> cq = cb.createQuery(BasketHasProduct.class); // select * from rate ..

        Root<BasketHasProduct> criteria = cq.from(BasketHasProduct.class);

        for (String field : new String[]{"basket_id", "product_id"}) {
            if (parameters.containsKey(field)) {
                String value = parameters.get(field);
                Integer integer = Integer.parseInt(value);

                Predicate constraint = cb.equal(criteria.get(field), integer);
                cq.where(constraint);
            }
        }

        TypedQuery<BasketHasProduct> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
