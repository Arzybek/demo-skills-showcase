package com.demo.showcase.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ShowEpisodesView {

    private UUID id;

    private final Integer episodesCount;

    private final Integer seasonsCount;

}
