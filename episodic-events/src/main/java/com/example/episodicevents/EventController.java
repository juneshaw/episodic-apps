package com.example.episodicevents;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class EventController {

    private final EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Event> getEvents() {
        return repository.findAll();
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return repository.save(event);
    }
}
