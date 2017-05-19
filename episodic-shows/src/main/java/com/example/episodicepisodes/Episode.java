package com.example.episodicepisodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "episodes")
//@Getter
//@Setter
//@NoArgsConstructor(force = true)
//@AllArgsConstructor
public class Episode {

    public Episode() {}

    public Episode(Long showId,
                   Integer seasonNumber,
                   Integer episodeNumber) {
        this.setShowId(showId);
        this.setSeasonNumber(seasonNumber);
        this.setEpisodeNumber(episodeNumber);
    }

    public Episode(Long id,
                   Long showId,
                   Integer seasonNumber,
                   Integer episodeNumber) {
        this.setId(id);
        this.setShowId(showId);
        this.setSeasonNumber(seasonNumber);
        this.setEpisodeNumber(episodeNumber);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "show_id")
//    @JsonIgnore
    @JsonProperty("id")
    Long showId;

    @JsonProperty("id")
    @JsonIgnore
    public Long getShowId() {
        return showId;
    }

    @JsonIgnore
    public void setShowId(Long showId) {
        this.showId = showId;
    }

    @Column(name = "season_number")
    Integer seasonNumber;

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
        this.setTitle(buildTitle());
    }

    @Column(name = "episode_number")
    Integer episodeNumber;

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
        this.setTitle(buildTitle());
    }

    @Transient
    String title;

//    @JsonIgnore
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String buildTitle() {
        return(
                "S" + this.getSeasonNumber() +
                " E" + this.getEpisodeNumber());
    }
}
