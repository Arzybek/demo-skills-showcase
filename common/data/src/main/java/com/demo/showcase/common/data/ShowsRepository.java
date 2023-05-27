package com.demo.showcase.common.data;

import com.demo.showcase.common.dto.ShowsView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShowsRepository {

    @PersistenceContext
    protected EntityManager em;

    public List<ShowsView> findAll() {
        return em.createQuery("""
                              select new com.demo.showcase.common.dto.ShowsView(
                              s.id, s.title, s.stage, s.genre, s.startDate, s.country, s.endDate, s.episodesCount, s.seasonsCount)
                              from ShowsEntity s
                              """, ShowsView.class)
                 .getResultList();
    }

}
