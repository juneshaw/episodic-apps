package com.example.episodicusers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) throws Exception {
        assertNotNull(userService);
        this.userService = userService;
    }

    @GetMapping
    public List<User> readAll() throws Exception {
        return userService.read();
    }

    @GetMapping(value = "/{userId}")
    public User read(@PathVariable Long userId) throws Exception {
        return userService.readOne(userId);
    }

    @PostMapping
    public User create(@RequestBody User user) throws Exception {
        return userService.create(user);
    }

}
