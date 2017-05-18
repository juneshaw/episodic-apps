package com.example.episodicshows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {

    @Autowired
    ShowRepository repository;

    public Show create(Show show) {
        return repository.save(show);
    }

    public Show read(Long id) {
        return repository.findOne(id);
    }

    public Long count() {
        return repository.count();
    }
}
