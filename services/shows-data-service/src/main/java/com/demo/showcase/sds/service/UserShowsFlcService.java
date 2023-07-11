package com.demo.showcase.sds.service;

import com.demo.showcase.common.data.ShowsRepository;
import com.demo.showcase.common.data.UserShowsRepository;
import com.demo.showcase.common.dto.ShowEpisodesView;
import com.demo.showcase.common.dto.UsersShowRequest;
import com.demo.showcase.common.sso.KeycloakUtils;
import com.demo.showcase.common.sso.exceptions.ForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserShowsFlcService {

    private final UserShowsRepository userShowsRepository;

    private final ShowsRepository showsRepository;

    public void checkShowDoesntExist(UUID showId) {
        if (userShowsRepository.exists(showId, KeycloakUtils.getUserId())) {
            throw new ForbiddenException("Данное шоу уже добавлено в вашу библиотеку");
        }
    }

    public void checkShowExists(UUID showId) {
        if (!userShowsRepository.exists(showId, KeycloakUtils.getUserId())) {
            throw new ForbiddenException("Данное шоу не добавлено в вашу библиотеку");
        }
    }

    public void checkShowsEpisodesAndSeasons(UsersShowRequest usersShowRequest) {
        ShowEpisodesView episodesInfo = showsRepository.getEpisodesInfoById(usersShowRequest.getShowId());
        if (usersShowRequest.getSeasonsWatched() != null
                && usersShowRequest.getSeasonsWatched() > episodesInfo.getSeasonsCount()) {
            throw new ForbiddenException("Количество сезонов превышает допустимое");
        }
        if (usersShowRequest.getEpisodesWatched() != null
                && usersShowRequest.getEpisodesWatched() > episodesInfo.getEpisodesCount()) {
            throw new ForbiddenException("Количество эпизодов превышает допустимое");
        }
    }

}
