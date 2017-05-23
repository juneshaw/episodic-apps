package com.example.episodicviewings;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ViewingRepository extends CrudRepository<Viewing, Long> {
    List<Viewing> findAll();
    Viewing findByUserIdAndShowId(Long userId, Long showId);
}
