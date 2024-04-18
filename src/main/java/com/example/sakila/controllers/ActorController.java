package com.example.sakila.controllers;

import com.example.sakila.dto.input.ActorInput;
import com.example.sakila.dto.input.PageInput;
import com.example.sakila.dto.output.ActorOutput;
import com.example.sakila.entities.Actor;
import com.example.sakila.repositories.ActorRepository;
import com.example.sakila.resthateoas.PageLink;
import com.example.sakila.services.actorService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import static com.example.sakila.dto.input.ValidationGroup.Create;
import static com.example.sakila.dto.output.ActorOutput.pagination;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actors")
//anything inside class - anything in this class is retrieved under this path
public class ActorController {
    //springboot magic
    @Autowired
    //automatically fill in field/variable
    private ActorRepository actorRepository;
    //when spring creates actor controller, create actor repository using actor repository
    //implement the interface (CRUD) within this repo
    @Autowired
    actorService service;


    private static final String MESSAGE = "Next Page: %s";
    @GetMapping
    public ResponseEntity<List<ActorOutput>> readAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "1") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        /*if (data.getPageNo()!=null) pageNo = data.getPageNo();
        if (data.getPageSize()!=null){pageSize = data.getPageSize();}*/
        List<ActorOutput> actoroutput = service.getAllActors(pageNo, pageSize, sortBy);
        HttpEntity<PageLink> ogg = pagination(pageNo,pageSize,sortBy);

        Map<List<ActorOutput>,Object> response = new HashMap<>();
        response.put(actoroutput, ogg);


        return new ResponseEntity<>(actoroutput,
                new HttpHeaders(),
                HttpStatus.OK);
    }
    //when someone request /actors, returns all actors

    @GetMapping("/{id}")
    public ActorOutput readById(@PathVariable Short id) {
        return actorRepository.findById(id)
                .map(ActorOutput::from)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such actor with id %d.", id)
                ));
    }

    private static final String TEMPLATE = "Hello, %s";
    @RequestMapping("/hello")
    public HttpEntity<PageLink> greeting(
            @RequestParam(value="name",defaultValue = "World") String name) {
        PageLink pagelink = new PageLink(String.format(TEMPLATE, name));
        Short page = 0;

        pagelink.add(linkTo(methodOn(ActorController.class).readById(page)).withSelfRel());
        return new ResponseEntity<>(pagelink, HttpStatus.OK);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorOutput create(@Validated(Create.class) @RequestBody ActorInput data) {
        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());
        final var saved = actorRepository.save(actor);
        return ActorOutput.from(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable Short id) {
        if (actorRepository.existsById(id)) {
            actorRepository.deleteById(id);
            return "All gone!";
        } else  {
            return "Error!";
        }
    }



    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable Short id, @Validated @RequestBody ActorInput data) {
        if (actorRepository.existsById(id))  {
            Actor actor = actorRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("No such actor with id %d.", id)
            ));
            if (data.getFirstName()!=null) {actor.setFirstName(data.getFirstName());}
            if (data.getLastName()!=null) {actor.setLastName(data.getLastName());}
            Actor save = actorRepository.save(actor);
            return "Yippee";
        } else  {
            return "Nope!";
        }
    }
}
