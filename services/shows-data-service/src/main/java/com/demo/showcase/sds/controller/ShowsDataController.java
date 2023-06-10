package com.demo.showcase.sds.controller;

import com.demo.showcase.common.dto.ShowRequestDto;
import com.demo.showcase.common.dto.ShowShortInfo;
import com.demo.showcase.common.dto.ShowView;
import com.demo.showcase.sds.api.ShowsDataApi;
import com.demo.showcase.sds.service.ShowsDataService;
import com.demo.showcase.sds.service.ShowsPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ShowsDataController implements ShowsDataApi {

    private final ShowsDataService showsDataService;

    private final ShowsPictureService showsPictureService;

    @Override
    public List<ShowShortInfo> getShortInfoAll() {
        return showsDataService.getAll();
    }

    @Override
    public ShowView getFullInfoById(UUID id) {
        return showsDataService.getFullInfoById(id);
    }

    @Override
    public void updateShowInfo(UUID id, ShowRequestDto showRequestDto) {
        showsDataService.updateShowInfo(id, showRequestDto);
    }

    @Override
    public void deleteShowInfo(UUID id) {
        showsDataService.deactualizeShowInfo(id);
    }

    @Override
    public ShowView createShow(ShowRequestDto showRequestDto) {
        return showsDataService.createShow(showRequestDto);
    }

    @Override
    public ResponseEntity<InputStreamResource> getImageByShowId(UUID id) {
        return showsPictureService.getPictureByShowId(id);
    }

    @Override
    public List<ShowShortInfo> find(String title) {
        return showsDataService.find(title);
    }
}
