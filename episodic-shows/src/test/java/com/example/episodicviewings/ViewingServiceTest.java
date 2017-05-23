package com.example.episodicviewings;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ViewingServiceTest {

    @Autowired
    ViewingRepository repository;

    @Autowired
    ViewingService viewingService;

    private Date updatedAt1 = Date.from(Instant.now());
    private Date updatedAt2 = Date.from(Instant.now());
    private Viewing viewing1;
    private Viewing viewing2;

    @Before
    public void setup() {
        viewing1 = new Viewing(
                1L,
                2L,
                3L,
                4L,
                updatedAt1,
                5);
        viewing2 = new
                Viewing(
                10L,
                2L,
                3L,
                4L,
                updatedAt2,
                15);
    }
    @Test
    @Transactional
    @Rollback
    public void testUpdate() throws Exception {
        Viewing viewing = repository.save(viewing1);

        Viewing updatedViewing = viewingService.update(viewing2);

        Viewing foundViewing = repository.findOne(updatedViewing.getId());
        assertThat(updatedViewing.getUserId(), equalTo(viewing2.getUserId()));
        assertThat(updatedViewing.getShowId(), equalTo(viewing2.getShowId()));
        assertThat(updatedViewing.getEpisodeId(), equalTo(viewing2.getEpisodeId()));
        assertThat(updatedViewing.getUpdatedAt(), equalTo(viewing2.getUpdatedAt()));
        assertThat(updatedViewing.getTimecode(), equalTo(viewing2.getTimecode()));
    }
}
