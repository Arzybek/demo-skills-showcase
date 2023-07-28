package com.demo.showcase.sds.service;

import com.demo.showcase.common.data.UserShowsEntity;
import com.demo.showcase.common.data.UserShowsRepository;
import com.demo.showcase.common.dto.UsersShowRequest;
import com.demo.showcase.common.dto.GetUserShowsResponse;
import com.demo.showcase.common.sso.KeycloakUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersDataService {

    private final UserShowsRepository repository;

    private final UserShowsFlcService flcService;

    @Transactional
    public UUID addShow(UsersShowRequest request){
        flcService.checkShowDoesntExist(request.getShowId());
        flcService.checkShowsEpisodesAndSeasons(request);
        UserShowsEntity userShowsEntity = createEntity(request);
        repository.persist(userShowsEntity);
        return userShowsEntity.getId();
    }

    public List<GetUserShowsResponse> getUserShows() {
        return repository.getUserShows(KeycloakUtils.getUserId());
    }

    @Transactional
    public void deleteUserShow(UUID id) {
        log.info("Пользователь {} удалил запись о сериале с id {}", KeycloakUtils.getUserId(), id);
        repository.deactualizeById(id);
    }

    @Transactional
    public void updateShowInfo(UUID showId, UsersShowRequest usersShowRequest) {
        flcService.checkShowExists(showId);
        flcService.checkShowsEpisodesAndSeasons(usersShowRequest);
        repository.updateShowInfo(KeycloakUtils.getUserId(), showId, usersShowRequest);
    }

    private UserShowsEntity createEntity(UsersShowRequest request){
        UUID id = UUID.randomUUID();
        UserShowsEntity userShowsEntity = new UserShowsEntity();
        userShowsEntity.setId(id);
        userShowsEntity.setUserId(KeycloakUtils.getUserId());
        userShowsEntity.setShowId(request.getShowId());
        userShowsEntity.setEpisodesWatched(request.getEpisodesWatched());
        userShowsEntity.setSeasonsWatched(request.getSeasonsWatched());
        userShowsEntity.setState(request.getState());
        userShowsEntity.setScore(request.getScore());
        return userShowsEntity;
    }

}
