package com.example.sakila.resthateoas;

import com.example.sakila.dto.output.ActorOutput;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class PageLink extends RepresentationModel<PageLink> {
    private final String content;

    @JsonCreator
    public PageLink(@JsonProperty("content") String content) {
        this.content = content;
    }

    public  String getContent() {
        return content;
    }
}
