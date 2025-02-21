package ru.sber.arch.restExaple.dto;

import lombok.Data;
import ru.sber.arch.restExaple.model.Client;
import ru.sber.arch.restExaple.model.Session;

@Data
public class RegisterRequest {

    private Client client;
    private Session session;

}
