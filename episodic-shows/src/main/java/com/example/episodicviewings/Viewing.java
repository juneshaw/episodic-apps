package com.example.episodicviewings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Viewing {

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