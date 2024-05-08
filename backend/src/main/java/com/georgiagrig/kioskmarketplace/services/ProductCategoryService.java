package com.georgiagrig.kioskmarketplace.services;

import com.georgiagrig.kioskmarketplace.entities.ProductCategory;
import com.georgiagrig.kioskmarketplace.repositories.ProductCategoryRepository;
import com.georgiagrig.kioskmarketplace.requests.create.ProductCategoryCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.ProductCategoryPatchRequest;
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
public class ProductCategoryService {
    @Autowired
    ProductCategoryRepository repository;

    @PersistenceContext
    private EntityManager entityManager;


    public Iterable<ProductCategory> list() {
        Iterable<ProductCategory> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<ProductCategory> find(Integer id) {
        ProductCategory result = repository.findByIdOrFail(id);

        ArrayList<ProductCategory> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(ProductCategoryCreateRequest request) {
        ProductCategory entity = new ProductCategory();
        entity.setName(request.getName());
        entity.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        repository.save(entity);
        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, ProductCategoryPatchRequest request) {
        ProductCategory entity = repository.findByIdOrFail(id);

        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        repository.save(entity);
    }


    public Iterable<ProductCategory> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductCategory> cq = cb.createQuery(ProductCategory.class); // select * from rate ..

        Root<ProductCategory> criteria = cq.from(ProductCategory.class);

        for (Map.Entry<String,String> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equalsIgnoreCase("name")) {
                Predicate constraint = cb.like(criteria.get("name"), "%" + value + "%");
                cq.where(constraint);
            }
        }

        TypedQuery<ProductCategory> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

}

