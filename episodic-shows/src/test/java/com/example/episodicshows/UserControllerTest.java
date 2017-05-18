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

import static org.hamcrest.core.IsNull.notNullValue;
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

	@Before
	public void setup() {
		mockedUser = new User("mocked@gmail.com");
		when(userService.create(anyObject())).thenReturn(mockedUser);
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
		String json = gson.toJson(mockedUser);
		RequestBuilder request = MockMvcRequestBuilders
				.get("/users/1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
				.andExpect(status().isOk());
	}

}
