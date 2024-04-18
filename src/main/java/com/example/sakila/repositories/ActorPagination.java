package com.example.sakila.repositories;

import com.example.sakila.entities.Actor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActorPagination extends PagingAndSortingRepository<Actor, Short> {
}
