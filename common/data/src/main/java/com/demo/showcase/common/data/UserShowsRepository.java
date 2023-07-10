package com.demo.showcase.common.data;

import com.demo.showcase.common.dto.GetUserShowsResponse;
import com.demo.showcase.common.dto.UsersShowRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.Query;

@Repository
public class UserShowsRepository extends BaseRepository<UserShowsEntity> {

    public boolean exists(UUID showId, UUID userId) {
        return em.createQuery("""
                              select 1 from UserShowsEntity u where u.showId = :showId and u.userId =: userId and u.isDeleted = false
                              """)
                 .setParameter("userId", userId)
                 .setParameter("showId", showId)
                 .getResultStream()
                 .findFirst()
                 .isPresent();
    }

    public List<GetUserShowsResponse> getUserShows(UUID userId) {
        return em.createQuery("""
                              select new com.demo.showcase.common.dto.GetUserShowsResponse(u.id, u.showId, s.title, u.seasonsWatched, u.episodesWatched)
                              from UserShowsEntity u
                              inner join ShowEntity s on u.showId = s.id 
                              where u.userId = :userId and u.isDeleted = false
                              """, GetUserShowsResponse.class)
                 .setParameter("userId", userId)
                 .getResultList();
    }

    public void deactualizeById(UUID id) {
        em.createQuery("update UserShowsEntity u set u.isDeleted = true where u.id = :id")
          .setParameter("id", id)
          .executeUpdate();
    }

    public void updateShowInfo(UUID userId, UUID showId, UsersShowRequest dto) {
        StringBuilder querySb = new StringBuilder("""
                                                  update UserShowsEntity u
                                                  set 
                                                  """);
        Map<String, Object> params = new HashMap<>();
        if (dto.getEpisodesWatched() != null) {
            querySb.append("u.episodesWatched = :ep");
            params.put("ep", dto.getEpisodesWatched());
        }
        if (dto.getSeasonsWatched() != null) {
            querySb.append(", u.seasonsWatched = :se");
            params.put("se", dto.getSeasonsWatched());
        }
        querySb.append(" where u.showId =:showId");
        querySb.append(" and u.userId =: userId");
        params.put("showId", showId);
        params.put("userId", userId);

        Query query = em.createQuery(querySb.toString());
        params.forEach(query::setParameter);
        query.executeUpdate();
    }
}
