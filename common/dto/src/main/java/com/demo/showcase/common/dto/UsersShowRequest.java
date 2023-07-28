package com.demo.showcase.common.dto;

import com.demo.showcase.common.enums.WatchState;
import lombok.Data;

import java.util.UUID;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UsersShowRequest {

    @NotNull
    private UUID showId;

    @Positive
    private Integer seasonsWatched;

    @Positive
    @NotNull
    private Integer episodesWatched;

    @NotNull
    private WatchState state;

    @Min(1)
    @Max(10)
    private Integer score;
}
