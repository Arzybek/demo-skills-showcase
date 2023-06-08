package com.demo.showcase.common.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class BaseRepository<T> {

    @PersistenceContext
    protected EntityManager em;

    public T persist(T e) {
        em.persist(e);
        return e;
    }

}
