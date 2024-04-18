package com.example.sakila.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.example.sakila.dto.input.ValidationGroup.Create;

//include getter and setter annotations from lombok
@Data
public class ActorInput {
    //don't need to specify id because specified by database
    //id should be immutable therefore shouldn't change
    @NotNull(groups = {Create.class})
    @Size(min = 1, max = 45)
    private String firstName;
    //should be not null and varchar(45)

    @NotNull(groups = {Create.class})
    //pass in identifier to say which group annotations belongs to
    //when validation passed, can say which group validating for
    @Size(min = 1, max = 45)
    private String lastName;
}
