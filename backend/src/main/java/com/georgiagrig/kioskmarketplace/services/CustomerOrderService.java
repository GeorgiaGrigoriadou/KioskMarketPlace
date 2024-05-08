package com.georgiagrig.kioskmarketplace.services;
import com.georgiagrig.kioskmarketplace.entities.CustomerOrder;
import com.georgiagrig.kioskmarketplace.entities.CustomerOrder;
import com.georgiagrig.kioskmarketplace.entities.OrderStatus;
import com.georgiagrig.kioskmarketplace.entities.User;
import com.georgiagrig.kioskmarketplace.repositories.CustomerOrderRepository;
import com.georgiagrig.kioskmarketplace.repositories.CustomerOrderRepository;
import com.georgiagrig.kioskmarketplace.repositories.OrderStatusRepository;
import com.georgiagrig.kioskmarketplace.repositories.UserRepository;
import com.georgiagrig.kioskmarketplace.requests.create.CustomerOrderCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.create.CustomerOrderCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.CustomerOrderPatchRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.CustomerOrderPatchRequest;
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
public class CustomerOrderService {
    @Autowired
    private CustomerOrderRepository repository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    public Iterable<CustomerOrder> list() {
        Iterable<CustomerOrder> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<CustomerOrder> find(Integer id) {
        CustomerOrder result = repository.findByIdOrFail(id);

        ArrayList<CustomerOrder> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(CustomerOrderCreateRequest request) {
        OrderStatus orderStatus = orderStatusRepository.findByIdOrFail(request.getOrder_status_id());
        User user = userRepository.findByIdOrFail(request.getCustomer_id());

        CustomerOrder entity = new CustomerOrder();
        entity.setTotal_price(request.getTotal_price());
        entity.setUser(user);
        entity.setOrderStatus(orderStatus);
        entity.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        repository.save(entity);
        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, CustomerOrderPatchRequest request) {
        CustomerOrder entity = repository.findByIdOrFail(id);

        if (request.getTotal_price() != null) {
            entity.setTotal_price(request.getTotal_price());
        }

        if (request.getOrder_status_id() != null) {
            OrderStatus newStatus = orderStatusRepository.findByIdOrFail(request.getOrder_status_id());
            entity.setOrderStatus(newStatus);
        }
        repository.save(entity);
    }


    public Iterable<CustomerOrder> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerOrder> cq = cb.createQuery(CustomerOrder.class); // select * from rate ..

        Root<CustomerOrder> criteria = cq.from(CustomerOrder.class);

        for (String field : new String[]{"total_price"}) {
            if (parameters.containsKey(field)) {
                String value = parameters.get(field);
                Double d = Double.parseDouble(value);

                Predicate constraint = cb.equal(criteria.get(field), d);
                cq.where(constraint);
            }
        }

        TypedQuery<CustomerOrder> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
