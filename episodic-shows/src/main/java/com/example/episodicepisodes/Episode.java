package com.example.episodicepisodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    Long id;

    @Column(name = "show_id")
    @JsonProperty("id")
    Long showId;

    @Column(name = "season_number")
    Integer seasonNumber;

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
        this.setTitle(buildTitle());
    }

    @Column(name = "episode_number")
    Integer episodeNumber;

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
        this.setTitle(buildTitle());
    }

    @Transient
    String title;

    private String buildTitle() {
        return(
                "S" + this.getSeasonNumber() +
                " E" + this.getEpisodeNumber());
    }
}
