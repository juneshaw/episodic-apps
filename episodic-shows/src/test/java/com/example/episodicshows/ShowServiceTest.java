package com.example.episodicshows;

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
public class ShowServiceTest {

    @Autowired
    ShowRepository repository;

    @Autowired
    ShowService showService;

    Show mockedShow;
    Show mockedShow2;

    @Before
    public void setup() {
        mockedShow = new Show("Test Show");
        mockedShow2 = new Show("Test 2 Show");
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


    @Test
    @Transactional
    @Rollback
    public void testReadAll() {
        repository.save(mockedShow);
        repository.save(mockedShow2);
        List<Show> shows = showService.readAll();

        Iterator<Show> showIterator = shows.listIterator();
        assertThat(shows.size(), equalTo(2));
        assertThat(
                showIterator.next().getName(),
                equalTo(mockedShow2.getName()));
        assertThat(
                showIterator.next().getName(),
                equalTo(mockedShow.getName()));

    }

    @Test
    @Transactional
    @Rollback
    public void testReadOne() {
        Show savedShow = repository.save(mockedShow);
        Show retrievedShow = showService.read(savedShow.getId());
        assertThat(retrievedShow, is(notNullValue()));
        assertThat(retrievedShow.getName(), equalTo(savedShow.getName()));
    }
}