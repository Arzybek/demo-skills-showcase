package com.demo.showcase.common.dto;

import com.demo.showcase.common.enums.ShowGenre;
import com.demo.showcase.common.enums.ShowStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShowView {

    private final UUID id;

    private final String title;

    private final ShowStage stage;

    private final ShowGenre genre;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final LocalDate startDate;

    private final String country;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final LocalDate endDate;

    private final Integer episodesCount;

    private final Integer seasonsCount;

}

