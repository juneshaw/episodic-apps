package com.example.episodicusers;

import com.example.episodicepisodes.Episode;
import com.example.episodicepisodes.EpisodeService;
import com.example.episodicviewings.Viewing;
import com.example.episodicviewings.ViewingService;
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

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	@MockBean
	ViewingService viewingService;

	@MockBean
	EpisodeService episodeService;

	Gson gson;
	private User mockedUser1;
	private User mockedUser2;
	private List<User> users;
	private Viewing viewing1;
	private Viewing viewing2;
	private Date updatedAt = Date.from(Instant.now());
	private Episode episode1 = new Episode(
			10L,
			3L,
			3,
			4);

	@Before
	public void setup() throws Exception {
		gson = new GsonBuilder().create();
		mockedUser1 = new User("mocked@gmail.com");
		mockedUser2 = new User("mocked2@me.com");
		viewing1 = new Viewing(1L,
				mockedUser1.getId(),
				3L,
				4L,
				updatedAt,
				0);
		viewing2 = new Viewing(1L,
				mockedUser1.getId(),
				3L,
				4L,
				updatedAt,
				0);
		users = Arrays.asList(mockedUser1, mockedUser2);
		when(userService.create(anyObject())).thenReturn(mockedUser1);
		when(userService.read()).thenReturn(users);
		when(userService.readOne(anyLong())).thenReturn(mockedUser1);
		when(episodeService.read(anyLong())).thenReturn(episode1);
		when(viewingService.update(anyObject())).thenReturn(viewing1);
	}

	@Test
	public void post() throws Exception {
		String json = gson.toJson(mockedUser1);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email", is(mockedUser1.getEmail())))
				.andExpect(jsonPath("$.id", notNullValue()));
	}

	@Test
	public void get() throws Exception {
		String json = gson.toJson(users);
		RequestBuilder request = MockMvcRequestBuilders
				.get("/users")
				.accept(MediaType.APPLICATION_JSON);
		 mockMvc.perform(request)
				 .andExpect(status().isOk())
				 .andExpect(jsonPath("$[0].email", is(mockedUser1.getEmail())))
				 .andExpect(jsonPath("$[1].email", is(mockedUser2.getEmail())));
	}
	@Test
	public void getOne() throws Exception {
		String json = gson.toJson(mockedUser1);
		RequestBuilder request = MockMvcRequestBuilders
				.get("/users/1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
				.andExpect(status().isOk());
	}

	@Test
	public void testViewingPatch() throws Exception {
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
//		String json = gson.toJson(viewing);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(viewing1);
		String url = "/users/" + mockedUser1.getId() + "/viewings";
		RequestBuilder request = MockMvcRequestBuilders
				.post(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andDo(print());
	}
}
