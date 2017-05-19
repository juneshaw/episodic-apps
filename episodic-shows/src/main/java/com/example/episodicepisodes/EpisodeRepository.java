package com.example.episodicepisodes;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EpisodeRepository extends CrudRepository<Episode, Long> {
    List<Episode> findAll();
}
