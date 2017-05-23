package com.example;

import lombok.Data;

import java.util.Date;

@Data
public class MessageEpisodicProgress {
    Long userId;
    Long episodeId;
    Date createdAt;
    Integer offset;
}


/*
  "userId": 12,
  "episodeId": 456,
  "createdAt": "2017-11-08T15:59:13.0091745",
  "offset": 4
 */