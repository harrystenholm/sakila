package com.example.sakila.dto.output;

import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FilmOutput {
    private Short id;
    private String title;
    private Integer releaseYear;
    private String rating;
    private String SpecialFeatures;
    private String language;
    private String description;
    private List<ActorInfoOutput> cast;
    private List<GenreOutput> genre;

    public static FilmOutput from(Film film) {
        return new FilmOutput(film.getId(),
                film.getTitle(),
                film.getReleaseYear(),
                film.getRating(),
                film.getSpecialFeatures(),
                film.getLanguage().getName(),
                film.getDescription(),
                film.getCast()
                        .stream()
                        .map(ActorInfoOutput::from)
                        .collect(Collectors.toList()),
                film.getCategories()
                        .stream()
                        .map(GenreOutput::from)
                        .collect(Collectors.toList())
        );
    }

}
