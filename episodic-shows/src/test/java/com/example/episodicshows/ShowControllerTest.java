package com.example.episodicshows;

import com.example.episodicepisodes.Episode;
import com.example.episodicepisodes.EpisodeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ShowController.class)
public class ShowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ShowService showService;

    @MockBean
    EpisodeService episodeService;

    private Show show1;
    private Show show2;
    private List<Show> shows;
    private Episode episode1;
    private Episode episode2;
    private List<Episode> episodes;
    private Gson gson;
    ObjectMapper mapper;

    @Before
    public void setup() {
        gson = new GsonBuilder().create();
        mapper = new ObjectMapper();
        show1 = new Show("test show");
        show2 = new Show("test show 2");
        shows = Arrays.asList(show1, show2);
        when(showService.create(anyObject())).thenReturn(show1);
        when(showService.read()).thenReturn(shows);
        when(showService.readOne(anyLong())).thenReturn(show1);

        Integer seasonNumber1 = 1;
        Integer episodeNumber1 = 2;
        Integer seasonNumber2 = 3;
        Integer episodeNumber2 = 4;
        episode1 =
                new Episode(
                        10L,
                        show1.getId(),
                        seasonNumber1,
                        episodeNumber2);
        episode2 =
                new Episode(
                        11L,
                        show1.getId(),
                        seasonNumber1,
                        episodeNumber2);
        episodes = Arrays.asList(
                episode1);
        when(episodeService.readByShowId(show1.getId())).thenReturn(episodes);
        when(episodeService.create(anyObject())).thenReturn(episode1);
    }

    @Test
    public void postCreateWorks() throws Exception {
        String json = gson.toJson(show1);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/shows")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(show1.getName())))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void read() throws Exception {
        String json = gson.toJson(shows);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/shows")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].name", is(show1.getName())))
                .andExpect(jsonPath("$[1].name", is(show2.getName())));
    }

    @Test
    public void readOne() throws Exception {
        String json = gson.toJson(show1);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/shows/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(json));
        ;
    }

    @Test
    public void readEpisodes() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/shows/" + show1.getId() + "/episodes")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath(
                        "$[0].id",
                        is(episodes.iterator().next().getId().intValue())))
                .andExpect(jsonPath(
                        "$[0].seasonNumber",
                        is(episodes.iterator().next().getSeasonNumber())))
                .andExpect(jsonPath(
                "$[0].episodeNumber",
                is(episodes.iterator().next().getEpisodeNumber())))
                .andExpect(jsonPath(
                        "$[0].title",
                        is(episodes.iterator().next().getTitle())));
    }

    @Test
    public void postEpisodes() throws Exception {
        String json = gson.toJson(episode1);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/shows/" + show2.getId() + "/episodes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",
                        is(episode1.getId().intValue())))
                .andExpect(jsonPath(
                        "$.seasonNumber",
                        is(episode1.getSeasonNumber())))
                .andExpect(jsonPath(
                        "$.episodeNumber",
                        is(episode1.getEpisodeNumber())))
                .andExpect(jsonPath(
                        "$.title",
                        is(episode1.getTitle())));
    }
}
