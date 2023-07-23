package com.demo.showcase.common.data;

import com.demo.showcase.common.enums.ShowGenre;
import com.demo.showcase.common.enums.ShowStage;
import com.demo.showcase.common.enums.WatchState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@ToString
@Table(name = "users_shows")
public class UserShowsEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "show_id")
    private UUID showId;

    @Column(name = "seasons_watched")
    private Integer seasonsWatched;

    @Column(name = "episodes_watched")
    private Integer episodesWatched;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private WatchState state;

}
