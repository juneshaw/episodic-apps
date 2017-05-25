package com.example.episodicevents;

import com.example.episodicevents.queue.MessageEpisodicProgress;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EventService {

    private final EventRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public EventService (EventRepository repository,
                         RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Iterable<Event> getEvents() {
        return repository.findAll();
    }

    public Iterable<Event> getRecentEvents() {
        return repository.findAll(new PageRequest(
                0,
                20,
                new Sort(Sort.Direction.DESC, "createdAt"))).getContent();
    }

    public Event postEvent(@RequestBody Event event) throws Exception {
        if (event.getType() == "progress") {
            Gson gson = new Gson();
            String json = gson.toJson(event);
            ProgressEvent progressEvent = gson.fromJson(json , ProgressEvent.class);
            MessageEpisodicProgress message = createMessageFromViewing(progressEvent);
            rabbitTemplate.convertAndSend("my-exchange", "my-routing-key", message);
        }
        return repository.save(event);
    }

    private MessageEpisodicProgress createMessageFromViewing(ProgressEvent progressEvent) {
        return new MessageEpisodicProgress(
                Long.valueOf(progressEvent.getUserId()),
                progressEvent.getEpisodeId(),
                progressEvent.getCreatedAt(),
                progressEvent.getData().getOffset());
    }
}
