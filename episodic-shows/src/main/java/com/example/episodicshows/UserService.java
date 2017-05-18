package com.example.episodicshows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    @Autowired
    UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    public User read(Long id) {
        return repository.findOne(id);
    }
//
//    public Iterable<User> readAll() {
//        return repository.findAll();
//    }
}
