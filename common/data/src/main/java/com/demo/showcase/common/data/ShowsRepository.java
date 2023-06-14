package com.demo.showcase.common.data;

import com.demo.showcase.common.dto.ShowRequestDto;
import com.demo.showcase.common.dto.ShowShortInfo;
import com.demo.showcase.common.dto.ShowView;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.Query;

@Repository
public class ShowsRepository extends BaseRepository<ShowEntity> {

    public List<ShowShortInfo> findAll() {
        return em.createQuery("""
                              select new com.demo.showcase.common.dto.ShowShortInfo(
                              s.id, s.title, s.stage, s.genre)
                              from ShowEntity s
                              where s.isDeleted = false
                              """, ShowShortInfo.class)
                 .getResultList();
    }

    public List<ShowShortInfo> findCaseInsensitiveBy(String title) {
        return em.createQuery("""
                              select new com.demo.showcase.common.dto.ShowShortInfo(
                              s.id, s.title, s.stage, s.genre)
                              from ShowEntity s
                              where lower(s.title) like concat('%',:title,'%') and s.isDeleted = false
                              """, ShowShortInfo.class)
                 .setParameter("title", title.toLowerCase().strip())
                 .getResultList();
    }

    public ShowView getFullInfoById(UUID id) {
        return em.createQuery("""
                              select new com.demo.showcase.common.dto.ShowView(
                              s.id, s.title, s.stage, s.genre, s.startDate, s.country, s.endDate, s.episodesCount, s.seasonsCount)
                              from ShowEntity s
                              where s.id = :id and s.isDeleted = false
                              """, ShowView.class)
                 .setParameter("id", id)
                 .getSingleResult();
    }

    public void updateShowInfo(UUID id, ShowRequestDto dto) {
        StringBuilder querySb = new StringBuilder("""
                                                  update ShowEntity s
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

    public void deactualizeById(UUID id) {
        em.createQuery("update ShowEntity s set s.isDeleted = true where s.id = :id")
          .setParameter("id", id)
          .executeUpdate();
    }

}
