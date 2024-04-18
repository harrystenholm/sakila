package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actor")
@Getter
@Setter
//automatically generates getter functions for whole class
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //automatically generates id num based on mysql input
    //GenerationType.AUTO would be if we wanted to do this on our end only
    @Column(name ="actor_id")
    private Short id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name ="last_name")
    private  String lastName;

    //many to many therefore have to create bridge table
    //through @jointable
    @ManyToMany
    @JoinTable(
            name="film_actor",
            joinColumns = {@JoinColumn(name="actor_id")},
            inverseJoinColumns = {@JoinColumn(name="film_id")}
    )
    private List<Film> films = new ArrayList<>();
}
