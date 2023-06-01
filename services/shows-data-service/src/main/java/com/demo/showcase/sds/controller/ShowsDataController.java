package com.demo.showcase.sds.controller;

import com.demo.showcase.common.dto.ShowsShortInfo;
import com.demo.showcase.common.dto.ShowsView;
import com.demo.showcase.sds.api.ShowsDataApi;
import com.demo.showcase.sds.service.ShowsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ShowsDataController implements ShowsDataApi {

    private final ShowsDataService showsDataService;

    @Override
    public List<ShowsShortInfo> getShortInfoAll() {
        return showsDataService.getAll();
    }

    @Override
    public ShowsView getFullInfoById(UUID id) {
        return showsDataService.getFullInfoById(id);
    }

    @Override
    public List<ShowsShortInfo> find(String title) {
        return showsDataService.find(title);
    }
}
