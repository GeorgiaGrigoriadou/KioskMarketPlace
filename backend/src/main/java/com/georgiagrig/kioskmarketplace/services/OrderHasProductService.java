package com.georgiagrig.kioskmarketplace.services;
import com.georgiagrig.kioskmarketplace.entities.CustomerOrder;
import com.georgiagrig.kioskmarketplace.entities.OrderHasProduct;
import com.georgiagrig.kioskmarketplace.entities.OrderHasProduct;
import com.georgiagrig.kioskmarketplace.entities.Product;
import com.georgiagrig.kioskmarketplace.repositories.CustomerOrderRepository;
import com.georgiagrig.kioskmarketplace.repositories.OrderHasProductRepository;
import com.georgiagrig.kioskmarketplace.repositories.OrderHasProductRepository;
import com.georgiagrig.kioskmarketplace.repositories.ProductRepository;
import com.georgiagrig.kioskmarketplace.requests.create.OrderHasProductCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.OrderHasProductPatchRequest;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Map;

@Service
public class OrderHasProductService {
    @Autowired
    private OrderHasProductRepository repository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<OrderHasProduct> list() {
        Iterable<OrderHasProduct> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<OrderHasProduct> find(Integer id) {
       OrderHasProduct result = repository.findByIdOrFail(id);

        ArrayList<OrderHasProduct> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(OrderHasProductCreateRequest request) {
        CustomerOrder customerOrder = customerOrderRepository.findByIdOrFail(request.getOrder_id());
        Product product = productRepository.findByIdOrFail(request.getProduct_id());

        OrderHasProduct entity = new OrderHasProduct();
        entity.setOrder(customerOrder);
        entity.setProduct(product);
        entity.setQuantity(request.getQuantity());
        repository.save(entity);
        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, OrderHasProductPatchRequest request) {
       OrderHasProduct entity = repository.findByIdOrFail(id);

        if (request.getQuantity() != null) {
            entity.setQuantity(request.getQuantity());
        }
        repository.save(entity);
    }


    public Iterable<OrderHasProduct> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderHasProduct> cq = cb.createQuery(OrderHasProduct.class); // select * from rate ..

        Root<OrderHasProduct> criteria = cq.from(OrderHasProduct.class);

        for (String field : new String[]{"quantity"}) {
            if (parameters.containsKey(field)) {
                String value = parameters.get(field);
                Integer integer = Integer.parseInt(value);

                Predicate constraint = cb.equal(criteria.get(field), integer);
                cq.where(constraint);
            }
        }

        TypedQuery<OrderHasProduct> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
