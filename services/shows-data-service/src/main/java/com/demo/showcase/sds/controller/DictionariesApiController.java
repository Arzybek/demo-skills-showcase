package com.demo.showcase.sds.controller;

import com.demo.showcase.common.enums.ShowGenre;
import com.demo.showcase.common.enums.ShowStage;
import com.demo.showcase.common.enums.WatchState;
import com.demo.showcase.sds.api.DictionariesApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DictionariesApiController implements DictionariesApi {

    @Override
    public List<ShowGenre> getShowsGenres() {
        return Arrays.stream(ShowGenre.values())
                     .toList();
    }

    @Override
    public List<ShowStage> getShowsStages() {
        return Arrays.stream(ShowStage.values())
                     .toList();
    }

    @Override
    public List<WatchState> getStates() {
        return Arrays.stream(WatchState.values())
                     .toList();
    }
}
