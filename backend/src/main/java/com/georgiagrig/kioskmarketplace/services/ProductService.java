package com.georgiagrig.kioskmarketplace.services;

import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.entities.Product;
import com.georgiagrig.kioskmarketplace.entities.ProductCategory;
import com.georgiagrig.kioskmarketplace.repositories.MeasurementUnitRepository;
import com.georgiagrig.kioskmarketplace.repositories.ProductCategoryRepository;
import com.georgiagrig.kioskmarketplace.repositories.ProductRepository;
import com.georgiagrig.kioskmarketplace.requests.create.ProductCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.ProductPatchRequest;
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
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private MeasurementUnitRepository measurementUnitRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<Product> list() {
        Iterable<Product> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<Product> find(Integer id) {
        Product result = repository.findByIdOrFail(id);

        ArrayList<Product> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(ProductCreateRequest request) {
        Product entity = new Product();
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setName(request.getName());
        entity.setRetail_cost(request.getRetail_cost());
        entity.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));

        MeasurementUnit mu =measurementUnitRepository.findByIdOrFail(request.getMeasurement_unit_id());
        entity.setMeasurementUnit(mu);

        ProductCategory productCategory = productCategoryRepository.findByIdOrFail(request.getProduct_category_id());
        entity.setProductCategory(productCategory);

        repository.save(entity);
        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, ProductPatchRequest request) {
        Product entity = repository.findByIdOrFail(id);

        if (request.getName() != null) {
            entity.setName(request.getName());
        }

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }

        if (request.getPrice() != null) {
            entity.setPrice(request.getPrice());
        }

        if (request.getRetail_cost() != null) {
            entity.setRetail_cost(request.getRetail_cost());
        }

        if (request.getProduct_category_id() != null) {
            ProductCategory productCategory = productCategoryRepository.findByIdOrFail(request.getProduct_category_id());
            entity.setProductCategory(productCategory);
        }

        if (request.getMeasurement_unit_id() != null) {
            MeasurementUnit mu =measurementUnitRepository.findByIdOrFail(request.getMeasurement_unit_id());
            entity.setMeasurementUnit(mu);
        }

        repository.save(entity);
    }

    public Iterable<Product> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class); // select * from rate ..

        Root<Product> criteria = cq.from(Product.class);

        int predicates = 0;
        for (String field : new String[]{"description", "name"}) {
            if (parameters.containsKey(field)) {
                Predicate constraint = cb.like(criteria.get(field), "%" + parameters.get(field) + "%");
                cq.where(constraint);
                predicates++;
            }
        }

        for (String field : new String[]{"price", "retail_cost"}) {
            if (parameters.containsKey(field)) {
                String value = parameters.get(field);
                Double d = Double.parseDouble(value);

                Predicate constraint = cb.equal(criteria.get(field), d);
                cq.where(constraint);
                predicates++;
            }
        }

        if (predicates == 0) {
            throw new IllegalArgumentException("No predicates defined");
        }

        TypedQuery<Product> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}

