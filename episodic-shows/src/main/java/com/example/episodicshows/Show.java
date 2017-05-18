package com.example.episodicshows;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "shows")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    public Show(String name) {
        this.setId(0L);
        this.setName(name);
    }
}

