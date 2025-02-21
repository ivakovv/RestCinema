package ru.sber.arch.restExaple.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.sber.arch.restExaple.model.Client;
import ru.sber.arch.restExaple.model.Session;

@Data
public class TicketRequest {
    private Client client;
    private Session session;
    @NotNull(message = "Cost must be not null.")
    private int cost;
    @NotNull(message = "Seat must be not null.")
    private int seat;
}
