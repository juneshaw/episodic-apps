package com.example.episodicusers;

import com.example.episodicviewings.Viewing;
import com.example.episodicviewings.ViewingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ViewingService viewingService;

    public UserController(UserService userService,
                          ViewingService viewingService) throws Exception {
        assertNotNull(userService);
        assertNotNull(viewingService);
        this.userService = userService;
        this.viewingService = viewingService;
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

    @PostMapping("/{userId}/viewings")
    public Viewing getViewing(@PathVariable Long userId,
                              @RequestBody Viewing viewing) throws Exception {
        return viewingService.update(viewing);
    }
}
