package com.demo.showcase.common.data;

import com.demo.showcase.common.dto.GetUserShowsResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserShowsRepository extends BaseRepository<UserShowsEntity> {

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

}
