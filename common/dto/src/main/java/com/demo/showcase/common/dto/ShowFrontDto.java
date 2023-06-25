package com.demo.showcase.common.dto;

import com.demo.showcase.common.enums.ShowGenre;
import com.demo.showcase.common.enums.ShowStage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShowFrontDto {

    private final UUID id;

    private final String title;

    private final ShowStage stage;

    private final ShowGenre genre;

    private final String startDate;

    private final String country;

    private final String endDate;

    private final Integer episodesCount;

    private final Integer seasonsCount;

}
