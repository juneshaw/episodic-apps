package com.example.episodicviewings;

import com.example.MessageEpisodicProgress;
import com.example.episodicepisodes.Episode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewingService {

    private final ViewingRepository repository;

    public ViewingService(ViewingRepository viewingRepository) {
        this.repository = viewingRepository;
    }

    public Viewing create(Viewing viewing) throws Exception{
        return repository.save(viewing);
    }

    public Viewing read(Long viewingId) throws Exception {
        return repository.findOne(viewingId);
    }

    public List<Viewing> readAll() throws Exception {
        return repository.findAll();
    }

    public Viewing update(Viewing viewing) throws Exception {

        Viewing updatedViewing;
        Viewing foundViewing = repository.findByUserIdAndShowId(
                viewing.getUserId(), viewing.getShowId());

        if (foundViewing != null) {
            foundViewing.setUpdatedAt(viewing.getUpdatedAt());
            foundViewing.setTimecode(viewing.getTimecode());
            updatedViewing = repository.save(foundViewing);
        } else {
            updatedViewing = this.create(viewing);
        }
        return updatedViewing;
    }

    public void delete(Long viewingId) throws Exception {
        repository.delete(viewingId);
    }

    public List<Viewing> getRecentViewings(Long userId) throws Exception {
        return repository.findAllByUserIdOrderByUpdatedAt(userId);
    }
    public Long count() {
        return repository.count();
    }

    public Viewing createViewingFromMessage
            (MessageEpisodicProgress message,
             Episode episode) {
        Viewing viewing = repository.findByUserIdAndShowId(
                message.getUserId(),
                episode.getShowId());
        if (viewing == null) {
            viewing = new Viewing(
                    message.getUserId(),
                    episode.getShowId(),
                    episode.getId(),
                    message.getCreatedAt(),
                    message.getOffset());
        } else {
            viewing.setUpdatedAt(message.getCreatedAt());
            viewing.setTimecode(message.getOffset());
        }
        return repository.save(viewing);
    }
}
