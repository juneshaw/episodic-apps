package com.example.episodicviewings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "viewings")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Viewing {

    public Viewing(
            Long userId,
            Long showId,
            Long episodeId,
            Date updatedAt,
            Integer timecode) {
        this.setUserId(userId);
        this.setShowId(showId);
        this.setEpisodeId(episodeId);
        this.setUpdatedAt(updatedAt);
        this.setTimecode(timecode);
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name="user_id")
    @JsonIgnore
    private Long userId;

    @Column(name="show_id")
    @JsonIgnore
    private Long showId;

    @Column(name="episode_id")
    private Long episodeId;

    @Column(name="updated_at")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @Column
    private Integer timecode;
}