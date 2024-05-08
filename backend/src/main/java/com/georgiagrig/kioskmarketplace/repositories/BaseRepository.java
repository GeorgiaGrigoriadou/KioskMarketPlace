package com.georgiagrig.kioskmarketplace.repositories;

import com.georgiagrig.kioskmarketplace.entities.MeasurementUnit;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Random;

@NoRepositoryBean
public interface BaseRepository<EntityType, PrimaryKeyType> extends JpaRepository<EntityType, PrimaryKeyType>  {
    default EntityType findByIdOrFail(PrimaryKeyType id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }

    default void deleteOrFail(PrimaryKeyType id) {
        findById(id).orElseThrow(EntityNotFoundException::new);
        deleteById(id);
    }

    @Query("select e from #{#entityName} e ORDER BY RAND() LIMIT 1")
    EntityType random();
}
