package com.demo.showcase.sds.controller;

import com.demo.showcase.common.dto.AddShowRequest;
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
    public UUID addShow(AddShowRequest addShowRequest) {
        return usersDataService.addShow(addShowRequest);
    }

    @Override
    public List<GetUserShowsResponse> myShows() {
        return usersDataService.getUserShows();
    }

}
