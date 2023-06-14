package com.demo.showcase.common.data;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseRepository<T> {

    @PersistenceContext
    protected EntityManager em;

    public T persist(T e) {
        em.persist(e);
        return e;
    }

}
