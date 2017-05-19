package com.example.episodicepisodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "episodes")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Episode {

    public Episode(Long showId,
                   Integer seasonNumber,
                   Integer episodeNumber) {
        this.setShowId(showId);
        this.setSeasonNumber(seasonNumber);
        this.setEpisodeNumber(episodeNumber);
    }

    @Id
    @GeneratedValue
    Long id;

    @Column
    Long showId;

    @Column
    Integer seasonNumber;

    @Column
    Integer episodeNumber;

}
