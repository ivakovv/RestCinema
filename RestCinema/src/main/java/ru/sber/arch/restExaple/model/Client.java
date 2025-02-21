package ru.sber.arch.restExaple.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@Data
@EqualsAndHashCode
public class Client {
    @NotNull(message = "Name must be not null.")
    @Length(max = 255, message = "Name length must be smaller than 255 symbols.")
    private String name;
    @NotNull(message = "Surname must be not null.")
    @Length(max = 255, message = "Surname length must be smaller than 255 symbols.")
    private String surname;
    @NotNull(message = "Id must be not null.")
    private int id;


}
