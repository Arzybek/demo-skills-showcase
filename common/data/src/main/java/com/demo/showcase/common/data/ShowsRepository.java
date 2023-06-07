package com.demo.showcase.common.data;

import com.demo.showcase.common.dto.ShowRequestDto;
import com.demo.showcase.common.dto.ShowsShortInfo;
import com.demo.showcase.common.dto.ShowsView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void updateShowInfo(UUID id, ShowRequestDto dto) {
        StringBuilder querySb = new StringBuilder("""
                                                  update ShowsEntity s
                                                  set 
                                                  """);
        Map<String, Object> params = new HashMap<>();
        if (dto.getTitle() != null) {
            querySb.append("s.title = :title");
            params.put("title", dto.getTitle());
        }
        if (dto.getSeasonsCount() != null) {
            querySb.append(", s.seasonsCount = :seasonsCount");
            params.put("seasonsCount", dto.getSeasonsCount());
        }
        if (dto.getEpisodesCount() != null) {
            querySb.append(", s.episodesCount = :episodesCount");
            params.put("episodesCount", dto.getEpisodesCount());
        }
        if (dto.getEndDate() != null) {
            querySb.append(", s.endDate = :endDate");
            params.put("endDate", dto.getEndDate());
        }
        if (dto.getStartDate() != null) {
            querySb.append(", s.startDate = :startDate");
            params.put("startDate", dto.getStartDate());
        }
        if (dto.getCountry() != null) {
            querySb.append(", s.country = :country");
            params.put("country", dto.getCountry());
        }
        if (dto.getGenre() != null) {
            querySb.append(", s.genre = :genre");
            params.put("genre", dto.getGenre());
        }
        if (dto.getStage() != null) {
            querySb.append(", s.stage = :stage");
            params.put("stage", dto.getStage());
        }
        querySb.append(" where s.id =:id");
        params.put("id", id);

        Query query = em.createQuery(querySb.toString());
        params.forEach(query::setParameter);
        query.executeUpdate();
    }
}
