package com.example.episodicusers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserRepository repository;

    @Autowired
    UserService userService;

    User mockedUser;

    @Before
    public void setup() {
        mockedUser = new User("Test User");
    }

    @Test
    @Transactional
    @Rollback
    public void testCreate() {
        Long count = repository.count();
        User testUser;
        testUser = userService.create(mockedUser);

        assertThat(testUser.getEmail(), equalTo(mockedUser.getEmail()));
        assertThat(userService.count(), equalTo(count+1));
    }
}
