package com.example.episodicevents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ScrubEvent extends Event {
    private Data data;

    @Getter
    @Setter
    @NoArgsConstructor(force = true)
    @AllArgsConstructor
    public static class Data {
        private Integer startOffset;
        private Integer endOffset;
    }
}
