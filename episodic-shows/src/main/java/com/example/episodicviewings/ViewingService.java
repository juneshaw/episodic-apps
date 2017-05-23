package com.example.episodicviewings;

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

    public void delete(Viewing viewing) throws Exception {
        repository.delete(viewing.getId());
    }
}
