package ru.sber.arch.restExaple.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class Building {
    @NotNull(message = "Adress must be not null.")
    @Length(max = 255, message = "Address length must be smaller than 255 symbols.")
    private String address;
    private List<Room> rooms;
    private List<Film> films;

}
