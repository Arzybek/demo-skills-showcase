package com.demo.showcase.common.data;

import com.demo.showcase.common.enums.ShowGenre;
import com.demo.showcase.common.enums.ShowStage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Table(name = "shows")
public class ShowsEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "stage")
    @Enumerated(EnumType.STRING)
    private ShowStage stage;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private ShowGenre genre;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "country")
    private String country;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "episdoes_count")
    private Integer episodesCount;
}

