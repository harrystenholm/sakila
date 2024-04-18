package com.example.sakila.controllers;

import com.example.sakila.dto.input.FilmInput;
import com.example.sakila.dto.output.FilmOutput;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import com.example.sakila.repositories.FilmRepository;
import com.example.sakila.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import static com.example.sakila.dto.input.ValidationGroup.Create;


import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping
    public List<FilmOutput> readAll(){
        final var films = filmRepository.findAll();
        return films.stream()
                .map(FilmOutput::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FilmOutput getById(@PathVariable Short id) {
        return filmRepository.findById(id)
                .map(FilmOutput::from)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such film with id %d ", id)
        ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmOutput create(@Validated(Create.class) @RequestBody FilmInput data) {
        final var film = new Film();
        film.setTitle(data.getTitle());
        if (data.getReleaseYear()!=null){film.setReleaseYear(data.getReleaseYear());}
        if (data.getRating()!=null){film.setRating(data.getRating());}
        if (data.getSpecialFeatures()!=null){film.setSpecialFeatures(data.getSpecialFeatures());}
        if (data.getDescription()!=null){film.setDescription(data.getDescription());}
        Language language = languageRepository.findById(Byte.valueOf(data.getLanguage()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("blah blah blah ")
                ));
        if (data.getLanguage()!=null){film.setLanguage(language);}
        final var savedFilm = filmRepository.save(film);
        return FilmOutput.from(savedFilm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable Short id) {
        if (filmRepository.existsById(id)) {filmRepository.deleteById(id);
            return "All gone!";
        } else  {
            return "Error! Record Not Found";
        }
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable Byte id, @Validated @RequestBody FilmInput data) {
        Short Shortid = Short.valueOf(id);
        if (filmRepository.existsById(Shortid)) {
            Film film = filmRepository.findById(Shortid)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("No such film with id %d" + id)
                    ));
            Language language = languageRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("No such language with id %d" + id)
            ));
            if (data.getTitle()!=null) {film.setTitle(data.getTitle());}
            if (data.getReleaseYear()!=null) {film.setReleaseYear(data.getReleaseYear());}
            if (data.getRating()!=null) {film.setRating(data.getRating());}
            if (data.getSpecialFeatures()!=null) {film.setSpecialFeatures(data.getSpecialFeatures());}
            if (data.getLanguage()!=null) {language.setName(data.getLanguage());}
            if (data.getDescription()!=null) {film.setDescription(data.getDescription());}
            Film save = filmRepository.save(film);
            return "Yippee!";
        } else {return "Nope!";}
    }
}

    /*@GetMapping("rand")
    public String random(@PathVariable String )*/

