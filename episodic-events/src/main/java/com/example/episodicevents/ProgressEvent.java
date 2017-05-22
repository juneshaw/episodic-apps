package com.example.episodicevents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProgressEvent extends Event {
    private PlayData data;


    @Getter
    @Setter
    public static class PlayData {
        private Integer offset;
    }
}
