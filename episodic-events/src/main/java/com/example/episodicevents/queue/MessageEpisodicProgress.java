package com.example.episodicevents.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MessageEpisodicProgress {
    Long userId;
    Long episodeId;
    Date createdAt;
    Integer offset;
}
