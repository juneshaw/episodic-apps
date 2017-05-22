package com.example.episodicviewings;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewingService {

    private final ViewingRepository repository;

    public ViewingService(ViewingRepository viewingRepository) {
        this.repository = viewingRepository;
    }

//    public Viewing findViewing(Long episodeId, Long UserId) {
//
//    }
    public Viewing create(Viewing viewing) throws Exception{
        return repository.save(viewing);
    }

    public Viewing read(Long viewingId) throws Exception {
        Viewing viewing = repository.findOne(viewingId);
        return viewing;
    }

    public List<Viewing> readAll() {
        return repository.findAll();
    }

    public Viewing update(Viewing viewing) {
        return repository.save(viewing);
    }
    public Viewing updateByUserAndShow(Viewing viewing) throws Exception {
        Viewing newViewing = repository.findByUserIdAndShowId(
                viewing.getUserId(),
                viewing.getShowId());
        if (newViewing!=null) {
            newViewing.setEpisodeId(viewing.getEpisodeId());
            newViewing.setUpdatedAt(viewing.getUpdatedAt());
            newViewing.setTimecode(viewing.getTimecode());
            return this.update(viewing);
        } else {
            return this.create(viewing);
        }
    }

    public void delete(Viewing viewing) throws Exception {
        repository.delete(viewing.getId());
    }
}
