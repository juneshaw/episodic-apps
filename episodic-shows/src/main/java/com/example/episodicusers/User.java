package com.example.episodicusers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    public User(String email) {
        this.setId(0L);
        this.setEmail(email);
    }
}
