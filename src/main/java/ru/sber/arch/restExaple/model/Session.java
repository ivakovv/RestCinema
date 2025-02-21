package ru.sber.arch.restExaple.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class Session {
    private Film film;
    private Room room;
    private String date;

}
