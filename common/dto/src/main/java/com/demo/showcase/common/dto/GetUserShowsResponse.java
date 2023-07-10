package com.demo.showcase.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetUserShowsResponse {

    private UUID id;

    private UUID showId;

    private String showTitle;

    private Integer seasonsWatched;

    private Integer episodesWatched;

    private Integer seasonsCount;

    private Integer episodesCount;

}
