package com.example.episodicevents;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EventService eventService;

    PlayEvent playEvent;
    PauseEvent pauseEvent;
    ProgressEvent progressEvent;
    RewindEvent rewindEvent;
    ScrubEvent scrubEvent;

    @Before
    public void setup() {
        Date dateField = new Date().from(Instant.now());
        playEvent = new PlayEvent(new PlayEvent.Data(1));
        pauseEvent = new PauseEvent(new PauseEvent.Data(2));
        progressEvent = new ProgressEvent(new ProgressEvent.Data(3));
        rewindEvent = new RewindEvent(new RewindEvent.Data(4, 5, 6.0));
        scrubEvent = new ScrubEvent(new ScrubEvent.Data(1, 2));

        List<Event> events = Arrays.asList(
                playEvent, pauseEvent, progressEvent, rewindEvent, scrubEvent);
        for (Event event : events) {
            event.setId("id1");
            event.setCreatedAt(dateField);
            event.setEpisodeId(1L);
            event.setShowId("showId1");
            event.setUserId("userId1");
        }
        playEvent.setType("play");
        pauseEvent.setType("pause");
        progressEvent.setType("progress");
        rewindEvent.setType("rewind");
        scrubEvent.setType("scrub");
        when(eventService.postEvent(anyObject())).thenReturn(playEvent);
    }

    @Test
    public void postTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(playEvent);
        RequestBuilder request = MockMvcRequestBuilders
                .post("")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(playEvent.getId())))
                .andExpect(jsonPath("$.episodeId", is(playEvent.getEpisodeId().intValue())))
                .andExpect(jsonPath("$.showId", is(playEvent.getShowId())))
                .andExpect(jsonPath("$.userId", is(playEvent.getUserId())))
                .andExpect(jsonPath("$.data.offset", is(playEvent.getData().getOffset())));

    }
}
