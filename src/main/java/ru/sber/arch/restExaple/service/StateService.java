package ru.sber.arch.restExaple.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.sber.arch.restExaple.exceptions.ResourceNotFoundException;
import ru.sber.arch.restExaple.exceptions.register.RegisterError;
import ru.sber.arch.restExaple.model.Client;
import ru.sber.arch.restExaple.model.Session;
import ru.sber.arch.restExaple.model.Ticket;

import java.util.*;

@Service
@Getter
public class StateService {

    private Set<Client> clients = new HashSet<>();
    private List<Session> sessions = new ArrayList<>();

    public Ticket register(Client client, Session session) throws RegisterError {
        Optional<Client> optionalClient = clients.stream()
                .filter(c -> c.getId() == client.getId())
                .findFirst();
        if (optionalClient.isPresent()) {
            throw new RegisterError(400, "Client already registered.");
        }
        else {
            clients.add(client);
            sessions.add(session);
            Ticket ticket = new Ticket();
            ticket.setClient(client);
            ticket.setSession(session);
            return ticket;
        }
    }

    public Client getClient(int idClient) throws ResourceNotFoundException {
        Optional<Client> optionalClient = clients.stream()
                .filter(c -> c.getId() == idClient)
                .findFirst();

        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new ResourceNotFoundException(404, "Client not found.");
        }
    }

    public void deleteClient(int idClient) throws ResourceNotFoundException {
        Optional<Client> optionalClient = clients.stream()
                .filter(c -> c.getId() == idClient)
                .findFirst();

        if (optionalClient.isPresent()) {
            clients.remove(optionalClient.get());
        } else {
            throw new ResourceNotFoundException(404, "Client not found.");
        }
    }

    public void updateClient(int idClient, Client client) throws ResourceNotFoundException {
        Optional<Client> optionalClient = clients.stream()
                .filter(c -> c.getId() == idClient)
                .findFirst();

        if (optionalClient.isPresent()) {
            Client clientOb = optionalClient.get();

            if (client.getName() != null) {
                clientOb.setName(client.getName());
            }
            if (client.getSurname() != null) {
                clientOb.setSurname(client.getSurname());
            }

        } else {
            throw new ResourceNotFoundException(404, "Client not found.");
        }
    }

}
