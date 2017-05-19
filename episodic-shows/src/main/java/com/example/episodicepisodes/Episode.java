package com.example.episodicepisodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "episodes")
@Getter
@Setter
//@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Episode {

    public Episode() {}

    public Episode(Long showId,
                   Integer seasonNumber,
                   Integer episodeNumber) {
        this.setShowId(showId);
        this.setSeasonNumber(seasonNumber);
        this.setEpisodeNumber(episodeNumber);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "show_id")
    Long showId;

    @Column(name = "season_number")
    Integer seasonNumber;

    @Column(name = "episode_number")
    Integer episodeNumber;

}
