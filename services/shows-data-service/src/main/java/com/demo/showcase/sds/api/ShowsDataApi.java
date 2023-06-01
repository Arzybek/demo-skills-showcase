package com.demo.showcase.sds.api;

import com.demo.showcase.common.dto.ShowsShortInfo;
import com.demo.showcase.common.dto.ShowsView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RequestMapping(ShowsDataApi.SHOWS_DATA_API_PATH)
@Tag(name = "Методы для работы с сериалами", description = ShowsDataApi.SHOWS_DATA_API_PATH)
public interface ShowsDataApi {

    String SHOWS_DATA_API_PATH = "/shows";

    @GetMapping
    @Operation(summary = "Получение краткой информации о всех зарегистрированных сериалах")
    List<ShowsShortInfo> getShortInfoAll();

    @GetMapping("/{id}")
    @Operation(summary = "Получение краткой информации о всех зарегистрированных сериалах")
    ShowsView getFullInfoById(@PathVariable UUID id);

    @GetMapping("/{id}/image")
    @Operation(summary = "Получение постера шоу")
    ResponseEntity<InputStreamResource> getImageByShowId(@PathVariable UUID id);

    @GetMapping("/search")
    @Operation(summary = "Поиск по сериалам")
    List<ShowsShortInfo> find(@RequestParam(value="title") String title);
}
