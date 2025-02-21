package ru.sber.arch.restExaple.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.sber.arch.restExaple.exceptions.ResourceNotFoundException;
import ru.sber.arch.restExaple.exceptions.register.RegisterError;
import ru.sber.arch.restExaple.model.Client;
import ru.sber.arch.restExaple.model.Session;
import ru.sber.arch.restExaple.model.Ticket;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Getter
public class TicketService {
    private Set<Ticket> tickets = new HashSet<>();

    public Ticket getTicket(int seat) throws ResourceNotFoundException {
        Optional<Ticket> optionalTicket = tickets.stream()
                .filter(t -> t.getSeat() == seat)
                .findFirst();

        if (optionalTicket.isPresent()) {
            return optionalTicket.get();
        } else {
            throw new ResourceNotFoundException(404, "Ticket not found.");
        }
    }

    public Ticket addTicket(Client client, Session session, int cost, int seat) throws RegisterError {
        Optional<Ticket> optionalTicket = tickets.stream()
                .filter(t -> t.getClient().equals(client)
                        && t.getSeat() == seat
                        && t.getSession().equals(session))
                .findFirst();
        if (optionalTicket.isPresent()) {
            throw new RegisterError(400, "Ticket already added.");
        }
        else {
            Ticket ticket = new Ticket();
            ticket.setClient(client);
            ticket.setSession(session);
            ticket.setSeat(seat);
            ticket.setCost(cost);
            return ticket;
        }
    }

    public void deleteTicket(int seat) throws ResourceNotFoundException {
        Optional<Ticket> optionalTicket = tickets.stream()
                .filter(t ->  t.getSeat() == seat)
                .findFirst();
        if (optionalTicket.isPresent()) {
            tickets.remove(optionalTicket.get());
        }
        else {
            throw new ResourceNotFoundException(404, "Ticket not found.");
        }
    }
}
