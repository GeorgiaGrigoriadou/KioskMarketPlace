package com.georgiagrig.kioskmarketplace.services;

import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import com.georgiagrig.kioskmarketplace.repositories.MeasurementUnitRepository;
import com.georgiagrig.kioskmarketplace.requests.create.MeasurementUnitCreateRequest;
import com.georgiagrig.kioskmarketplace.requests.patch.MeasurementUnitPatchRequest;
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
public class MeasurementUnitService {
    @Autowired
    MeasurementUnitRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<MeasurementUnit> list() {
        Iterable<MeasurementUnit> dataset = repository.findAll();
        return dataset;
    }

    public Iterable<MeasurementUnit> find(Integer id) {
        MeasurementUnit result = repository.findByIdOrFail(id);

        ArrayList<MeasurementUnit> list = new ArrayList<>();
        list.add(result);

        return list;
    }

    public Integer create(MeasurementUnitCreateRequest request) {
        MeasurementUnit entity = new MeasurementUnit();
        entity.setCode(request.getCode());
        entity.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        repository.save(entity);
        return entity.getId();
    }

    public void delete(Integer id) {
        repository.deleteOrFail(id);
    }

    public void patch(Integer id, MeasurementUnitPatchRequest request) {
        MeasurementUnit entity = repository.findByIdOrFail(id);

        if (request.getCode() != null) {
            entity.setCode(request.getCode());
        }
        repository.save(entity);
    }


    public Iterable<MeasurementUnit> find(Map<String, String> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MeasurementUnit> cq = cb.createQuery(MeasurementUnit.class); // select * from rate ..

        Root<MeasurementUnit> criteria = cq.from(MeasurementUnit.class);

        for (Map.Entry<String,String> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equalsIgnoreCase("code")) {
                Predicate constraint = cb.like(criteria.get("code"), "%" + value + "%");
                cq.where(constraint);
            }
        }

        TypedQuery<MeasurementUnit> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
