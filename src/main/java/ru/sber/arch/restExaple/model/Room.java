package ru.sber.arch.restExaple.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Room {
    @NotNull(message = "Number must be not null.")
    private int number;

    private static final int MAX_SEATS = 50;

    private int[] seats = new int[MAX_SEATS];

}
