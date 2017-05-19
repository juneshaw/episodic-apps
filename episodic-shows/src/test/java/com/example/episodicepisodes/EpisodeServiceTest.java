package com.example.episodicepisodes;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class EpisodeServiceTest {

    @Autowired
    EpisodeRepository repository;

    @Autowired
    EpisodeService episodeService;

    Episode episode1, episode2;

    @Before
    public void setup() {
        episode1 = new Episode(1L, 2, 3);
        episode2 = new Episode(4L, 5, 6);

    }

    @Transactional
    @Rollback
    @Test
    public void testCreate() {
        episodeService.create(episode1);
        Episode retrievedEpisode = repository.findByShowId(episode1.getShowId());
        assertThat(
                retrievedEpisode,
                equalTo(episode1));
    }
}
