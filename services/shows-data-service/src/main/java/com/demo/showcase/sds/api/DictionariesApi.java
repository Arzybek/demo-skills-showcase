package com.demo.showcase.sds.api;

import com.demo.showcase.common.enums.ShowGenre;
import com.demo.showcase.common.enums.ShowStage;
import com.demo.showcase.common.enums.WatchState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(DictionariesApi.DICTS_API_PATH)
@Tag(name = "Методы для работы с перечислениями API", description = DictionariesApi.DICTS_API_PATH)
public interface DictionariesApi {

    String DICTS_API_PATH = "/api/dictionaries";

    @GetMapping("/genres")
    @Operation(summary = "Получение списка жанров сериалов")
    List<ShowGenre> getShowsGenres();

    @GetMapping("/stages")
    @Operation(summary = "Получение списка статусов шоу")
    List<ShowStage> getShowsStages();

    @GetMapping("/watchStates")
    @Operation(summary = "Получение списка статусов шоу в списке юзера")
    List<WatchState> getStates();

    @GetMapping("/scores")
    @Operation(summary = "Получение возможных оценок")
    List<Integer> getScores();
}
