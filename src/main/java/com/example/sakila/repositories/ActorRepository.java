package com.example.sakila.repositories;

import com.example.sakila.entities.Actor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Short>, PagingAndSortingRepository<Actor,Short> {
    //extends crud repository allows use of crud repository code

}
//this actually talks to the database