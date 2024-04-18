package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="film")
@Getter
@Setter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id")
    private Short id;
    //lombok shortcut @ Getter automatically runs code below for each column
    //public Short getId() {
    //    return id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private Integer releaseYear;

    /*@ManyToMany
    @JoinTable(
            name="film_actor",
            joinColumns = {@JoinColumn(name="film_id")},
            inverseJoinColumns = {@JoinColumn(name="actor_id")}
    )*/
    //if many to many relationship already
    @ManyToMany(mappedBy = "films")
    private List<Actor> cast = new ArrayList<>();

    @ManyToMany(mappedBy = "films")
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="language_id")
    private Language language;

    @Column(name="rating")
    private String rating;

    @Column(name="special_features")
    private String specialFeatures;
}
