package com.demo.showcase.common.dto;

import lombok.Data;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AddShowRequest {

    @NotNull
    private UUID showId;

    @Positive
    private Integer seasonsWatched;

    @Positive
    private Integer episodesWatched;

}
