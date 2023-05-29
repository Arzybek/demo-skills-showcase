package com.demo.showcase.sds.controller;

import com.demo.showcase.common.dto.ShowsView;
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
    public List<ShowsView> getAll() {
        return showsDataService.getAll();
    }

    @Override
    public List<ShowsView> find(String title) {
        return showsDataService.find(title);
    }
}
