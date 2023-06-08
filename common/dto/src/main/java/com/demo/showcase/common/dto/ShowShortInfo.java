package com.demo.showcase.common.dto;

import com.demo.showcase.common.enums.ShowGenre;
import com.demo.showcase.common.enums.ShowStage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ShowShortInfo {

    private final UUID id;

    private final String title;

    private final ShowStage stage;

    private final ShowGenre genre;

}
