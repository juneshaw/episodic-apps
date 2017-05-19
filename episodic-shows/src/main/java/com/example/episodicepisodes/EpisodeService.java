package com.example.episodicepisodes;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {

    private final EpisodeRepository repository;

    public EpisodeService(EpisodeRepository repository) {
        this.repository = repository;
    }

    public Episode create(Episode episode) {
        return repository.save(episode);
    }

    public List<Episode> readAll() {
        return repository.findAll();
    }

    public Episode read(Long episodeId) {
        return repository.findOne(episodeId);
    }
}
