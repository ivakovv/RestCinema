package ru.sber.arch.restExaple.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Film {

    private String genre;
    @NotNull(message = "Title must be not null.")
    @Length(max = 255, message = "Title length must be smaller than 255 symbols.")
    private String title;
    private String description;
    @NotNull(message = "Title must be not null.")
    private int duration;

}
