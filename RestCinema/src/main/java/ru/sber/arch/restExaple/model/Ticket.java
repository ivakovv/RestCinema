package ru.sber.arch.restExaple.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Ticket {

    private Client client;
    private Session session;
    @NotNull(message = "Cost must be not null.")
    private int cost;
    @NotNull(message = "Seat must be not null.")
    private int seat;

}
