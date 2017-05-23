package com.example.episodicviewings;

import com.example.episodicepisodes.Episode;
import com.example.episodicepisodes.EpisodeService;
import com.example.episodicshows.Show;
import com.example.episodicshows.ShowService;
import com.example.episodicusers.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ViewingController.class)
public class ViewingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ViewingService viewingService;

	@MockBean
	EpisodeService episodeService;

	@MockBean
	ShowService showService;

	Gson gson;
	private User user1;
	private Viewing viewing1;
	private Viewing viewing2;
	private Date updatedAt1 = Date.from(Instant.now());
	private Date updatedAt2 = Date.from(Instant.now());
	private Episode episode1;
	private Show show1;
	List<RecentViewing> recentViewings;
	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() throws Exception {

		user1 = new User("mocked@gmail.com");
		viewing1 = new Viewing(1L,
				user1.getId(),
				3L,
				4L,
				updatedAt1,
				1);
		viewing2 = new Viewing(1L,
				user1.getId(),
				3L,
				4L,
				updatedAt2,
				2);
		List<Viewing> viewings =
				Arrays.asList(
						viewing1,
						viewing2);
		episode1 = new Episode(
				10L,
				3L,
				3,
				4);
		show1 = new Show(
				3L,
				"ESPN Special");
		RecentViewing recentViewing1 = new RecentViewing(
				show1,
				episode1,
				viewing1.getUpdatedAt(),
				viewing1.getTimecode());
		RecentViewing recentViewing2 = new RecentViewing(
				show1,
				episode1,
				viewing2.getUpdatedAt(),
				viewing2.getTimecode());
		recentViewings = Arrays.asList(
				recentViewing1,
				recentViewing2);

		when(episodeService.read(anyLong())).thenReturn(episode1);
		when(viewingService.getRecentViewings(anyLong())).thenReturn(viewings);
		when(showService.read(anyLong())).thenReturn(show1);
		when(episodeService.read(anyLong())).thenReturn(episode1);
	}

	@Test
	public void testViewingPatch() throws Exception {
		String json = mapper.writeValueAsString(viewing1);
		String url = "/users/" + user1.getId() + "/viewings";
		RequestBuilder request = MockMvcRequestBuilders
				.patch(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void getRecentViewings() throws Exception {
		String json = mapper.writeValueAsString(recentViewings);
		String url = "/users/" + user1.getId() + "/recently-watched";
		RequestBuilder request = MockMvcRequestBuilders
				.get(url)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().json(json));
	}
}
