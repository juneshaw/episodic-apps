package com.example.episodicusers;

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
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	private User mockedUser;
	private User mockedUser2;
	private List<User> users;

	@Before
	public void setup() {
		mockedUser = new User("mocked@gmail.com");
		mockedUser2 = new User("mocked2@me.com");
		users = Arrays.asList(mockedUser, mockedUser2);
		when(userService.create(anyObject())).thenReturn(mockedUser);
		when(userService.read()).thenReturn(users);
		when(userService.readOne(anyLong())).thenReturn(mockedUser);
	}

	@Test
	public void postCreateWorks() throws Exception {
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(mockedUser);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email", CoreMatchers.is(mockedUser.getEmail())))
				.andExpect(jsonPath("$.id", notNullValue()));
	}

	@Test
	public void read() throws Exception {
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(users);
		RequestBuilder request = MockMvcRequestBuilders
				.get("/users")
				.accept(MediaType.APPLICATION_JSON);
		 mockMvc.perform(request)
				 .andExpect(status().isOk())
				 .andExpect(jsonPath("$[0].email", CoreMatchers.is(mockedUser.getEmail())))
				 .andExpect(jsonPath("$[1].email", CoreMatchers.is(mockedUser2.getEmail())));
	}
	@Test
	public void readOne() throws Exception {
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(mockedUser);
		RequestBuilder request = MockMvcRequestBuilders
				.get("/users/1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
				.andExpect(status().isOk());
	}

}
