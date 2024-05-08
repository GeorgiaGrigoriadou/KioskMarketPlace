package com.georgiagrig.kioskmarketplace.services;
import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.entities.Product;
import com.georgiagrig.kioskmarketplace.entities.ProductCategory;
import com.georgiagrig.kioskmarketplace.entities.SpecialOffer;
import com.georgiagrig.kioskmarketplace.repositories.ProductRepository;
import com.georgiagrig.kioskmarketplace.repositories.SpecialOfferRepository;
import com.georgiagrig.kioskmarketplace.requests.create.SpecialOfferCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.SpecialOfferPatchRequest;
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
public class SpecialOfferService {
    @Autowired
    private SpecialOfferRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<SpecialOffer> list() {
        Iterable<SpecialOffer> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<SpecialOffer> find(Integer id) {
        SpecialOffer result = repository.findByIdOrFail(id);

        ArrayList<SpecialOffer> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(SpecialOfferCreateRequest request) {
        Product product = productRepository.findByIdOrFail(request.getProduct_id());

        SpecialOffer entity = new SpecialOffer();
        entity.setProduct(product);
        entity.setDiscount(request.getDiscount());
        entity.setPoints(request.getPoints());
        entity.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        entity.setDateStart(request.getOfferDateStart());
        entity.setDateEnd(request.getOfferDateEnd());
        repository.save(entity);
        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, SpecialOfferPatchRequest request) {
        SpecialOffer entity = repository.findByIdOrFail(id);

        if (request.getDiscount() != null) {
            entity.setDiscount(request.getDiscount());
        }

        if (request.getPoint() != null) {
            entity.setPoints(request.getPoint());
        }

        if (request.getOfferDateStart() != null) {
            entity.setDateStart(request.getOfferDateStart());

        }

        if (request.getOfferDateEnd() != null) {
            entity.setDateEnd(request.getOfferDateEnd());
        }
        repository.save(entity);
    }


    public Iterable<SpecialOffer> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SpecialOffer> cq = cb.createQuery(SpecialOffer.class); // select * from rate ..

        Root<SpecialOffer> criteria = cq.from(SpecialOffer.class);

        int predicates = 0;

        for (String field : new String[]{"discount"}) {
            if (parameters.containsKey(field)) {
                String value = parameters.get(field);
                Double d = Double.parseDouble(value);

                Predicate constraint = cb.equal(criteria.get(field), d);
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

        TypedQuery<SpecialOffer> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}


