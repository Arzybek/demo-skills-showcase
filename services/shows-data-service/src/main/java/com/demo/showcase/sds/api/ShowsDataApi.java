package com.demo.showcase.sds.api;

import com.demo.showcase.common.data.views.ShowsView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ShowsDataApi.SHOWS_DATA_API_PATH)
@Tag(name = "Методы для работы с сериалами", description = ShowsDataApi.SHOWS_DATA_API_PATH)
public interface ShowsDataApi {

    String SHOWS_DATA_API_PATH = "/shows";

    @GetMapping
    @Operation(summary = "Получение списка всех зарегистрированных сериалов")
    List<ShowsView> getAll();

//    @PostMapping
//    void createShow();
}
