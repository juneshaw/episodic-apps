package com.example.episodicusers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> read() {
        return userService.read();
    }

    @GetMapping(value = "/{userId}")
    public User read(@PathVariable Long userId) {
        return userService.readOne(userId);
    }

    @PostMapping
    public User create(@RequestBody User user) throws Exception {
        return userService.create(user);
    }

}
