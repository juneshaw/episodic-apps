package com.example.episodicevents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FastForwardEvent extends Event {
    private PlayData data;

    @Getter
    @Setter
    public static class PlayData {
        private Integer startOffset;
        private Integer endOffset;
        private Double speed;
    }
}