package com.demo.showcase.sds.controller;

import com.demo.showcase.common.dto.UsersShowRequest;
import com.demo.showcase.common.dto.GetUserShowsResponse;
import com.demo.showcase.sds.api.UsersDataApi;
import com.demo.showcase.sds.service.UsersDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UsersDataController implements UsersDataApi {

    private final UsersDataService usersDataService;

    @Override
    public UUID addShow(UsersShowRequest usersShowRequest) {
        return usersDataService.addShow(usersShowRequest);
    }

    @Override
    public List<GetUserShowsResponse> myShows() {
        return usersDataService.getUserShows();
    }

    @Override
    public void deleteUserShowInfo(UUID id) {
        usersDataService.deleteUserShow(id);
    }

    @Override
    public void updateShowInfo(UUID showId, UsersShowRequest usersShowRequest) {
        usersDataService.updateShowInfo(showId, usersShowRequest);
    }

}
