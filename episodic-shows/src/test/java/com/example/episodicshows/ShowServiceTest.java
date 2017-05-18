package com.example.episodicshows;

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
public class ShowServiceTest {

    @Autowired
    ShowRepository repository;

    @Autowired
    ShowService showService;

    Show mockedShow;

    @Before
    public void setup() {
        mockedShow = new Show("Test Show");
    }

    @Test
    @Transactional
    @Rollback
    public void testCreate() {
        Long count = repository.count();
        Show testShow;
        testShow = showService.create(mockedShow);

        assertThat(testShow.getName(), equalTo(mockedShow.getName()));
        assertThat(showService.count(), equalTo(count+1));
    }
}