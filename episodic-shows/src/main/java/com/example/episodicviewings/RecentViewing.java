package com.example.episodicviewings;

import com.example.episodicepisodes.Episode;
import com.example.episodicshows.Show;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class RecentViewing {
    private Show show;
    private Episode episode;
    private Date updatedAt;
    private Integer timecode;
}
