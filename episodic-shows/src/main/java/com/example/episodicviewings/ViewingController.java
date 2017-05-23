package com.example.episodicviewings;

import com.example.episodicepisodes.Episode;
import com.example.episodicepisodes.EpisodeService;
import com.example.episodicshows.Show;
import com.example.episodicshows.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("/users")
public class ViewingController {

    private final ViewingService viewingService;
    private final EpisodeService episodeService;
    private final ShowService showService;

    public ViewingController(ViewingService viewingService,
                             EpisodeService episodeService,
                             ShowService showService) throws Exception {
        assertNotNull(viewingService);
        assertNotNull(episodeService);
        assertNotNull(showService);
        this.viewingService = viewingService;
        this.episodeService = episodeService;
        this.showService = showService;
    }

    @GetMapping
    public List<Viewing> readAll() throws Exception {
        return viewingService.readAll();
    }

    @GetMapping("/{viewingId}")
    public Viewing read(@PathVariable Long viewingId) throws Exception {
        return viewingService.read(viewingId);
    }

    @GetMapping("/{userId}/recently-watched")
    public List<RecentViewing> getRecentViewings(@PathVariable Long userId) throws Exception {
        List<Viewing> viewings = viewingService.getRecentViewings(userId);
        List<RecentViewing> recentViewings = viewings.stream()
                .map( viewing -> {
                            Show show = showService.read(viewing.getShowId());
                            Episode episode = episodeService.read(viewing.getEpisodeId());
                            return new RecentViewing(
                                    show,
                                    episode,
                                    viewing.getUpdatedAt(),
                                    viewing.getTimecode());
                        })
                .collect(Collectors.toList());
        return recentViewings;
    }


    @PatchMapping("/{userId}/viewings")
    public void updateViewing(@PathVariable Long userId,
                              @RequestBody Viewing viewing) throws Exception {

        Episode episode = episodeService.read(viewing.getEpisodeId());
        viewing.setUserId(userId);
        viewing.setShowId(episode.getShowId());
        viewing.setEpisodeId(episode.getId());
        viewingService.update(viewing);
    }

    @DeleteMapping
    public void deleteViewing(@PathVariable Long viewingId) throws Exception {
        viewingService.delete(viewingId);
    }
}
