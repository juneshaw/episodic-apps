package com.example.episodicepisodes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

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

    @Transactional
    @Rollback
    @Test
    public void testRead() {
        episodeService.create(episode1);
        episodeService.create(episode2);
        List<Episode> episodes = repository.findAll();
        assertThat(episodes.size(), equalTo(2));
        assertTrue(episodes.stream().anyMatch(item -> item.getShowId().equals(episode1.getShowId())));
        assertTrue(episodes.stream().anyMatch(item -> item.getShowId().equals(episode2.getShowId())));
        assertTrue(episodes.stream().anyMatch(item -> item.getSeasonNumber().equals(episode1.getSeasonNumber())));
        assertTrue(episodes.stream().anyMatch(item -> item.getSeasonNumber().equals(episode2.getSeasonNumber())));
        assertTrue(episodes.stream().anyMatch(item -> item.getEpisodeNumber().equals(episode1.getEpisodeNumber())));
        assertTrue(episodes.stream().anyMatch(item -> item.getEpisodeNumber().equals(episode2.getEpisodeNumber())));
    }

    @Transactional
    @Rollback
    @Test
    public void testReadOne() {
        Episode savedEpisode = repository.save(episode1);
        Episode retrievedEpisode = episodeService.read(savedEpisode.getId());
        assertThat(retrievedEpisode.getId(), equalTo(savedEpisode.getId()));
        assertThat(retrievedEpisode.getShowId(), equalTo(savedEpisode.getShowId()));
        assertThat(retrievedEpisode.getSeasonNumber(), equalTo(savedEpisode.getSeasonNumber()));
        assertThat(retrievedEpisode.getEpisodeNumber(), equalTo(savedEpisode.getEpisodeNumber()));
    }
}
