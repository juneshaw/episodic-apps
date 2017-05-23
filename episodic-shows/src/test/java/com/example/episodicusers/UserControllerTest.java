package com.example.episodicusers;

import com.example.episodicepisodes.EpisodeService;
import com.example.episodicviewings.ViewingService;
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
	private User user1;
	private User user2;
	private List<User> users;

	@Before
	public void setup() throws Exception {
		gson = new GsonBuilder().create();
		user1 = new User("mocked@gmail.com");
		user2 = new User("mocked2@me.com");
		users = Arrays.asList(user1, user2);
		when(userService.create(anyObject())).thenReturn(user1);
		when(userService.read()).thenReturn(users);
		when(userService.readOne(anyLong())).thenReturn(user1);
	}

	@Test
	public void post() throws Exception {
		String json = gson.toJson(user1);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email", is(user1.getEmail())))
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
				 .andExpect(jsonPath("$[0].email", is(user1.getEmail())))
				 .andExpect(jsonPath("$[1].email", is(user2.getEmail())));
	}
	@Test
	public void getOne() throws Exception {
		String json = gson.toJson(user1);
		RequestBuilder request = MockMvcRequestBuilders
				.get("/users/1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
				.andExpect(status().isOk());
	}
}
