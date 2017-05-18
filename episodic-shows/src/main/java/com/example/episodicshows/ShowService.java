package com.example.episodicshows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository repository;

    public Show create(Show show) {
        return repository.save(show);
    }

    public Show readOne(Long id) {
        return repository.findOne(id);
    }

    public List<Show> read() {
        return repository.findAll();
    }

    public Long count() {
        return repository.count();
    }
}
