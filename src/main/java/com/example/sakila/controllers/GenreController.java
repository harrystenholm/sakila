package com.example.sakila.controllers;

import com.example.sakila.dto.output.GenreOutput;
import com.example.sakila.repositories.CategoryRepository;
import com.example.sakila.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<GenreOutput> readAll(){
        final var categories = categoryRepository.findAll();
        return categories.stream()
                .map(GenreOutput::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GenreOutput getById(@PathVariable Short id) {
        return categoryRepository.findById(id)
                .map(GenreOutput::from)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such genre with id %d ", id)
                ));
    }
}
