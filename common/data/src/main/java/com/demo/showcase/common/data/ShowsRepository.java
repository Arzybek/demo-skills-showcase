package com.demo.showcase.common.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShowsRepository {

    @PersistenceContext
    protected EntityManager em;

    public List<ShowsEntity> findAll() {
        return em.createQuery("""
                              select s
                              from ShowsEntity s
                              """, ShowsEntity.class)
                 .getResultList();
    }

}
