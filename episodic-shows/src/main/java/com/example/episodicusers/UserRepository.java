package com.example.episodicusers;

import com.example.episodicusers.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
