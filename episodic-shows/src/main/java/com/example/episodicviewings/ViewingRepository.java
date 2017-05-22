package com.example.episodicviewings;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ViewingRepository extends CrudRepository<Viewing, Long> {
//    public List<Long> findByEpisode(Long episodeId);
//    public List<Long> findByUserId(Long userId);
    public List<Viewing> findAll();
    public Viewing findByUserIdAndShowId(Long userId, Long showId);
}
