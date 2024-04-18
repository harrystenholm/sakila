package com.example.sakila.controllers;

import com.example.sakila.resthateoas.PageLink;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class HelloController {

    private static final String TEMPLATE = "Hello, %s";

    @RequestMapping("/hello")
    public HttpEntity<PageLink> greeting(
            @RequestParam(value="name",defaultValue = "World") String name) {
        PageLink pagelink = new PageLink(String.format(TEMPLATE, name));

        pagelink.add(linkTo(methodOn(HelloController.class).greeting(name)).withSelfRel());
        return new ResponseEntity<>(pagelink, HttpStatus.OK);

    }


    @GetMapping("/greeting/")
    public String greet(@RequestParam(required = false) Integer page) {
        final var actualPage = page == null ? 1 : page;
        if (actualPage == 2) {
            return "Wow! you found the secret page!";
        } else if (actualPage == 6) {
            return "Stupid!!!!";
        } else return "Hello, World! Access Granted page " + actualPage;
    }

    @GetMapping("/access/{id}")
    public String welcome(@PathVariable Integer id) {
        final var actualId = id == null ? 1 : id;
        if (actualId == 2) {
            return "Wow! you found the secret page! But different.";
        } else if (actualId == 6) {
            return "Number Six! Wow!";
        } else return "What the hell?! " + actualId;
    }

    /*@GetMapping("/secret/{secret}")
    public String secret(@PathVariable Integer secret) {
        final var actualSecret = secret == null ? 1 : secret;
        if (actualSecret == 273) {
            int i = 0;
            while (i < 9) {
                int j = 0;
                while (j < 9) {
                    return " | X | ";
                    j = j+1;
                }
                return "\n";
                i = j+1;
            }
        } else if (actualSecret == 6) {
            return "Number Six! Wow!";
        } else return "What the hell?! " + actualSecret;
        return null;
    }*/
}
