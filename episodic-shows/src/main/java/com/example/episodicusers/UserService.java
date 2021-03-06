package com.example.episodicusers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    public List<User> read() {
        return repository.findAll();
    }

    public User readOne(Long id) {
        return repository.findOne(id);
    }

    public Long count() {
        return repository.count();
    }

}
