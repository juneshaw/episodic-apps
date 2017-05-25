package com.example.episodicevents;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class EventController {

    private final EventService eventService;
    private final RabbitTemplate rabbitTemplate;

    public EventController(EventService eventService,
                           RabbitTemplate rabbitTemplate) {
        this.eventService = eventService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public Iterable<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/recent")
    public Iterable<Event> getRecentEvents() {
        return eventService.getRecentEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.postEvent(event);
    }
}
