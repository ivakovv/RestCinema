package ru.sber.arch.restExaple.dto;

import lombok.Data;
import ru.sber.arch.restExaple.model.Film;


@Data
public class FilmRequest {
    private Film film;
}
