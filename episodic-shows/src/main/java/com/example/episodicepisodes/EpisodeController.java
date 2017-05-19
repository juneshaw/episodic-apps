package com.example.episodicepisodes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("/episodes")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) throws Exception {
        assertNotNull(episodeService);
        this.episodeService = episodeService;
    }

    @GetMapping
    public List<Episode> readAll() throws Exception {
        return episodeService.readAll();
    }

    @GetMapping("/{episodeId}")
    public Episode read(@PathVariable Long episodeId) throws Exception {
        return episodeService.read(episodeId);
    }

    @PostMapping
    public Episode create(@RequestBody Episode episode) {
        return episodeService.create(episode);
    }

}
