package com.demo.showcase.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class GetUserShowsResponse {

    @NotNull
    private UUID showId;

    @NotNull
    private String showTitle;

    @Positive
    private Integer seasonsWatched;

    @Positive
    private Integer episodesWatched;

}
