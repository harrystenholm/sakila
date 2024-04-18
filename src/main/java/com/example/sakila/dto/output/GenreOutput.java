package com.example.sakila.dto.output;

import com.example.sakila.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class GenreOutput {
    private Byte id;
    private String name;
    private List<FilmInfoOutput> films;

    public static GenreOutput from(Category category) {
        return new GenreOutput(
                category.getId(),
                category.getGenreName(),
                category.getFilms().
                        stream().
                        map(FilmInfoOutput::from).
                        collect(Collectors.toList())
        );
    }
}
