package com.example.sakila.dto.output;


import com.example.sakila.controllers.ActorController;
import com.example.sakila.entities.Actor;
import com.example.sakila.resthateoas.PageLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import com.example.sakila.resthateoas.PageLink;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ActorOutput {
    private Short id;
    private String firstName;
    private String lastName;
    private List<FilmInfoOutput> films;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public static HttpEntity<PageLink> pagination(Integer pageNo, Integer pageSize, String sortBy) {
        final String TEMPLATE = "Next Page: %d";
        PageLink pagelink = new PageLink(String.format(TEMPLATE, pageNo));
        pagelink.add(linkTo(methodOn(ActorController.class)
                .readAll(pageNo, pageSize, sortBy))
                .withSelfRel());
        return new ResponseEntity<>(pagelink, HttpStatus.OK);
    }

    public static ActorOutput from(Actor actor) {
        return new ActorOutput(actor.getId(),
                actor.getFirstName(),
                actor.getLastName(),
                actor.getFilms().stream()
                        .map(FilmInfoOutput::from)
                        .collect(Collectors.toList()));
    }


}
