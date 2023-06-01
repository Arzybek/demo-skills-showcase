package com.demo.showcase.common.data;

import com.demo.showcase.common.dto.ShowsShortInfo;
import com.demo.showcase.common.dto.ShowsView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ShowsRepository {

    @PersistenceContext
    protected EntityManager em;

    public List<ShowsShortInfo> findAll() {
        return em.createQuery("""
                              select new com.demo.showcase.common.dto.ShowsShortInfo(
                              s.id, s.title, s.stage, s.genre)
                              from ShowsEntity s
                              """, ShowsShortInfo.class)
                 .getResultList();
    }

    public List<ShowsShortInfo> find(String title) {
        return em.createQuery("""
                              select new com.demo.showcase.common.dto.ShowsShortInfo(
                              s.id, s.title, s.stage, s.genre)
                              from ShowsEntity s
                              where s.title like concat('%',:title,'%')
                              """, ShowsShortInfo.class)
                 .setParameter("title", title)
                 .getResultList();
    }

    public ShowsView getFullInfoById(UUID id) {
        return em.createQuery("""
                              select new com.demo.showcase.common.dto.ShowsView(
                              s.id, s.title, s.stage, s.genre, s.startDate, s.country, s.endDate, s.episodesCount, s.seasonsCount)
                              from ShowsEntity s
                              where s.id = :id
                              """, ShowsView.class)
                 .setParameter("id", id)
                 .getSingleResult();
    }
}
