package com.demo.showcase.sds.api;

import com.demo.showcase.common.dto.AddShowRequest;
import com.demo.showcase.common.dto.GetUserShowsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

@RequestMapping(UsersDataApi.USERS_DATA_API_PATH)
@Tag(name = "Методы для работы с сериалами юзера", description = UsersDataApi.USERS_DATA_API_PATH)
public interface UsersDataApi {

    String USERS_DATA_API_PATH = "/api/users/shows";

    @PostMapping()
    @Operation(summary = "Добавление информации о просмотре сериала")
    @PreAuthorize("hasAnyRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    UUID addShow(@Valid @RequestBody AddShowRequest addShowRequest);

    @GetMapping
    @Operation(summary = "Получение информации о сериалах")
    @PreAuthorize("hasAnyRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    List<GetUserShowsResponse> myShows();

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление информации о сериале")
    @PreAuthorize("hasAnyRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    void deleteUserShowInfo(@PathVariable UUID id);

}
