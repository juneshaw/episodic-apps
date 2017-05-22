package com.example.episodicevents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScrubEvent extends Event {
    private Data data;

    @Getter
    @Setter
    public static class Data {
        private Integer startOffset;
        private Integer endOffset;
    }
}
