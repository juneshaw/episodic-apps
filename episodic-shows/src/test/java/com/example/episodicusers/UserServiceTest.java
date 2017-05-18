package com.example.episodicusers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserRepository repository;

    @Autowired
    UserService userService;

    User mockedUser;
    User mockedUser2;

    @Before
    public void setup() {
        mockedUser = new User("Test User");
        mockedUser2 = new User("Test 2 User");
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

    @Test
    @Transactional
    @Rollback
    public void testReadAll() {
        repository.save(mockedUser);
        repository.save(mockedUser2);
        List<User> users = userService.read();

        Iterator<User> userIterator = users.listIterator();
        assertThat(users.size(), equalTo(2));
        assertThat(
                userIterator.next().getEmail(),
                equalTo(mockedUser2.getEmail()));
        assertThat(
                userIterator.next().getEmail(),
                equalTo(mockedUser.getEmail()));

    }

    @Test
    @Transactional
    @Rollback
    public void testReadOne() {
        User savedUser = repository.save(mockedUser);
        User retrievedUser = userService.readOne(savedUser.getId());
        assertThat(retrievedUser, is(notNullValue()));
        assertThat(retrievedUser.getEmail(), equalTo(savedUser.getEmail()));
    }
}
