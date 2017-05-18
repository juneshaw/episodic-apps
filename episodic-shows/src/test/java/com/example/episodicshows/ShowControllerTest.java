package com.example.episodicshows;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.CoreMatchers;
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

import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShowController.class)
public class ShowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ShowService showService;

    private Show mockedShow;
    private Show mockedShow2;
    private List<Show> shows;

    @Before
    public void setup() {
        mockedShow = new Show("test show");
        mockedShow2 = new Show("test show 2");
        shows = Arrays.asList(mockedShow, mockedShow2);
        when(showService.create(anyObject())).thenReturn(mockedShow);
        when(showService.read()).thenReturn(shows);
        when(showService.readOne(anyLong())).thenReturn(mockedShow);
    }

    @Test
    public void postCreateWorks() throws Exception {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(mockedShow);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/shows")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(mockedShow.getName())))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void read() throws Exception {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(shows);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/shows")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", CoreMatchers.is(mockedShow.getName())))
                .andExpect(jsonPath("$[1].name", CoreMatchers.is(mockedShow2.getName())));
    }

    @Test
    public void readOne() throws Exception {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(mockedShow);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/shows/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

}
