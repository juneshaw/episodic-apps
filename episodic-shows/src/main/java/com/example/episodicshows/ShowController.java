package com.example.episodicshows;

import com.example.episodicepisodes.Episode;
import com.example.episodicepisodes.EpisodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;
    private final EpisodeService episodeService;

    public ShowController(ShowService showService,
                          EpisodeService episodeService) {
        assertNotNull(showService);
        assertNotNull(episodeService);
        this.showService = showService;
        this.episodeService = episodeService;
    }

    @GetMapping
    public List<Show> readAll() {
        return showService.read();
    }

    @GetMapping("/{showId}")
    public Show read(@PathVariable Long showId) {
        return showService.readOne(showId);
    }

    @GetMapping("/{showId}/episodes")
    public List<Episode> readByShowId(@PathVariable Long showId) {
        List<Episode> episodeList =
                episodeService.readByShowId(showId);
        return episodeList;
    }

    @PostMapping
    public Show create(@RequestBody Show show) throws Exception {
        return showService.create(show);
    }

}
