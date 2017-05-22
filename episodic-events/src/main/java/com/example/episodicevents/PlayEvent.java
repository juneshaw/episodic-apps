package com.example.episodicevents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
public class PlayEvent extends Event {
    private PlayData data;

    @Getter
    @Setter
    public static class PlayData {
       private int offset;
    }

}
