package com.demo.showcase.common.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ShowsPicturesRepository {

    @PersistenceContext
    protected EntityManager em;

    public Optional<ShowsPicsEntity> getPictureById(UUID id) {
        return em.createQuery("""
                              select p
                              from ShowsPicsEntity p
                              where p.showId = :id
                              """, ShowsPicsEntity.class)
                 .setParameter("id", id)
                 .getResultStream()
                 .findFirst();
    }

}
