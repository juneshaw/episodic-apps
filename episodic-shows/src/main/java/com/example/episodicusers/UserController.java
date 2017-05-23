package com.example.episodicusers;

import com.example.episodicepisodes.EpisodeService;
import com.example.episodicviewings.ViewingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ViewingService viewingService;
    private final EpisodeService episodeService;

    public UserController(UserService userService,
                          ViewingService viewingService,
                          EpisodeService episodeService) throws Exception {
        assertNotNull(userService);
        assertNotNull(viewingService);
        assertNotNull(episodeService);
        this.userService = userService;
        this.viewingService = viewingService;
        this.episodeService = episodeService;
    }

    @GetMapping
    public List<User> readAll() throws Exception {
        return userService.read();
    }

    @GetMapping("/{userId}")
    public User read(@PathVariable Long userId) throws Exception {
        return userService.readOne(userId);
    }

    @PostMapping
    public User create(@RequestBody User user) throws Exception {
        return userService.create(user);
    }
}
