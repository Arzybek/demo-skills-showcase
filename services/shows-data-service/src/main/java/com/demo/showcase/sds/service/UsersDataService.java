package com.demo.showcase.sds.service;

import com.demo.showcase.common.data.ShowsRepository;
import com.demo.showcase.common.data.UserShowsEntity;
import com.demo.showcase.common.data.UserShowsRepository;
import com.demo.showcase.common.dto.AddShowRequest;
import com.demo.showcase.common.sso.KeycloakUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersDataService {

    private final UserShowsRepository repository;

    @Transactional
    public UUID addShow(AddShowRequest request){
        UUID id = UUID.randomUUID();
        UserShowsEntity userShowsEntity = new UserShowsEntity();
        userShowsEntity.setId(id);
        userShowsEntity.setUserId(KeycloakUtils.getUserId());
        userShowsEntity.setShowId(request.getShowId());
        userShowsEntity.setEpisodesWatched(request.getEpisodesWatched());
        userShowsEntity.setSeasonsWatched(request.getSeasonsWatched());
        repository.persist(userShowsEntity);
        return id;
    }

}
