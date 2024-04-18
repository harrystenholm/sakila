package com.example.sakila.dto.output;

import com.example.sakila.entities.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActorInfoOutput {
    private short id;
    private String fullName;

    public static ActorInfoOutput from(Actor actor) {
        return new ActorInfoOutput(
                actor.getId(),
                actor.getFirstName() + " " + actor.getLastName()
        );
    }
}
