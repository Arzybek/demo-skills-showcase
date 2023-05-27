package com.demo.showcase.sds.controller;

import com.demo.showcase.common.data.ShowsEntity;
import com.demo.showcase.sds.api.ShowsDataApi;
import com.demo.showcase.sds.service.ShowsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShowsDataController implements ShowsDataApi {

    private final ShowsDataService showsDataService;

    @Override
    public List<ShowsEntity> getAll() {
        return showsDataService.getAll();
    }
}
