package com.demo.showcase.sds.api;

import com.demo.showcase.common.dto.ShowRequestDto;
import com.demo.showcase.common.dto.ShowShortInfo;
import com.demo.showcase.common.dto.ShowView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

@RequestMapping(ShowsDataApi.SHOWS_DATA_API_PATH)
@Tag(name = "Методы для работы с сериалами", description = ShowsDataApi.SHOWS_DATA_API_PATH)
public interface ShowsDataApi {

    String SHOWS_DATA_API_PATH = "/shows";

    @GetMapping
    @Operation(summary = "Получение краткой информации о всех зарегистрированных сериалах")
    List<ShowShortInfo> getShortInfoAll();

    @GetMapping("/{id}")
    @Operation(summary = "Получение полной информации о сериале")
    ShowView getFullInfoById(@PathVariable UUID id);

    @PutMapping("/{id}")
    @Operation(summary = "Изменение информации о сериале")
    @ResponseStatus(HttpStatus.OK)
    void updateShowInfo(@PathVariable UUID id, @Valid @RequestBody ShowRequestDto showRequestDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление информации о сериале")
    @ResponseStatus(HttpStatus.OK)
    void deleteShowInfo(@PathVariable UUID id);

    @PostMapping()
    @Operation(summary = "Добавление информации о сериале")
    @ResponseStatus(HttpStatus.OK)
    UUID createShow(@Valid @RequestBody ShowRequestDto showRequestDto);

    @GetMapping("/{id}/image")
    @Operation(summary = "Получение постера шоу")
    ResponseEntity<InputStreamResource> getImageByShowId(@PathVariable UUID id);

    @GetMapping("/search")
    @Operation(summary = "Поиск по сериалам")
    List<ShowShortInfo> find(@RequestParam(value = "title") String title);
}
