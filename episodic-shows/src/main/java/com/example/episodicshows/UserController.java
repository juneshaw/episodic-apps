package com.example.episodicshows;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{userId}")
    public User read(@PathVariable Long userId) {
        return userService.read(userId);
    }

    @PostMapping
    public User create(@RequestBody User user) throws Exception {
        return userService.create(user);
    }

}
