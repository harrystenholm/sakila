package com.example.sakila.services;

import com.example.sakila.dto.output.ActorOutput;
import com.example.sakila.entities.Actor;
import com.example.sakila.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class actorService {
    @Autowired
    ActorRepository actorRepository;

    public List<ActorOutput> getAllActors(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Actor> pageResult = actorRepository.findAll(paging);


        if (pageResult.hasContent()) {
            return pageResult.stream()
                    .map(ActorOutput::from)
                    .collect(Collectors.toList());

        } else {
            return new ArrayList<ActorOutput>();
        }
    }
}

