package com.example.episodicepisodes;

import com.example.TestBaseClass;
import com.example.episodicshows.Show;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EpisodeController.class)
public class EpisodeControllerTest extends TestBaseClass {

    @Autowired
    MockMvc mvc;

    @MockBean
    EpisodeService episodeService;

    private Episode episode1, episode2;
    private List<Episode> episodeList;
    ObjectMapper mapper;

    @Before
    public void setup() {
        Show show1 = new Show(1L,"Cheers");
        Show show2 = new Show(2L, "Modern Family");
        episode1 =
                new Episode(
                        10L,
                        show1.getId(),
                        1,
                        2);

        episode2 =
                new Episode(
                        11L,
                        show2.getId(),
                        3,
                        4);
        episodeList =
                Arrays.asList(
                        episode1,
                        episode2);
        when(episodeService.readAll()).thenReturn(episodeList);
        when(episodeService.read(episode1.getId())).thenReturn(episode1);
        when(episodeService.create(anyObject())).thenReturn(episode2);

        mapper = new ObjectMapper();

    }

    @Test
    public void get() throws Exception {
        String json = mapper.writeValueAsString(episodeList);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/episodes")
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(json));
    }

    @Test
    public void getOne() throws Exception {
        String json = mapper.writeValueAsString(episode1);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/episodes/" + episode1.getId())
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(json));
    }

    @Test
    public void post() throws Exception {
        String jsonEpisode = mapper.writeValueAsString(episode2);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/episodes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEpisode);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(jsonEpisode));
    }
}

