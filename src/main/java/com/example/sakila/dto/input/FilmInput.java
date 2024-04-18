package com.example.sakila.dto.input;

import com.example.sakila.entities.Language;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.example.sakila.dto.input.ValidationGroup.Create;

@Data
public class FilmInput {

    @NotNull(groups = {Create.class})
    @Size(min=1,max=128)
    private String title;

    private Integer releaseYear;

    private String rating;

    private String SpecialFeatures;

    private String language;

    private String description;
}
