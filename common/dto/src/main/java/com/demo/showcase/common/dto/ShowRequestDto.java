package com.demo.showcase.common.dto;

import com.demo.showcase.common.enums.ShowGenre;
import com.demo.showcase.common.enums.ShowStage;
import lombok.Data;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
public class ShowRequestDto {

    @NotNull
    private String title;

    private ShowStage stage;

    private ShowGenre genre;

    private LocalDate startDate;

    private String country;

    private LocalDate endDate;

    @Positive
    private Integer episodesCount;

    @Positive
    private Integer seasonsCount;

}
