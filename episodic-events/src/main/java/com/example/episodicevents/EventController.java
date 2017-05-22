package com.example.episodicevents;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/recent")
    public Iterable<Event> getRecentEvents() {
        return repository.findAll(new PageRequest(
                0,
                20,
                new Sort(Sort.Direction.DESC, "createdAt")));
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return repository.save(event);
    }
}
