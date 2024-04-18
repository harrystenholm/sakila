package com.example.sakila.dto.output;

import com.example.sakila.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilmInfoOutput {
    private Short id;
    private String title;
    private String description;

    public static FilmInfoOutput from(Film film) {
        return new FilmInfoOutput(
                film.getId(),
                film.getTitle(),
                film.getDescription());
    }
}
