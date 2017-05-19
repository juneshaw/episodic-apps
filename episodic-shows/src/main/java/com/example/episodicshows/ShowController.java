package com.example.episodicshows;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        assertNotNull(showService);
        this.showService = showService;
    }

    @GetMapping
    public List<Show> readAll() {
        return showService.read();
    }

    @GetMapping("/{showId}")
    public Show read(@PathVariable Long showId) {
        return showService.readOne(showId);
    }

    @PostMapping
    public Show create(@RequestBody Show show) throws Exception {
        return showService.create(show);
    }

}
