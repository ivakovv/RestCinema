package ru.sber.arch.restExaple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.arch.restExaple.dto.RegisterRequest;
import ru.sber.arch.restExaple.dto.TicketRequest;
import ru.sber.arch.restExaple.exceptions.ResourceNotFoundException;
import ru.sber.arch.restExaple.exceptions.register.RegisterError;
import ru.sber.arch.restExaple.model.Ticket;
import ru.sber.arch.restExaple.service.TicketService;

import java.util.Set;


@RestController
@RequestMapping("/tickets")
public class TicketsConroller {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/getAllTickets")
    public Set<Ticket> getAllTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/getTicketBySeat")
    public ResponseEntity<?> getTicketBySeat(@RequestParam("seat") int seat) throws ResourceNotFoundException {
        try{
            return new ResponseEntity<>(ticketService.getTicket(seat), HttpStatus.OK);
        } catch(ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addTicket")
    public ResponseEntity<?> addTicket(@RequestBody TicketRequest request) {
        try {
            Ticket ticket = ticketService.addTicket(request.getClient(), request.getSession(), request.getCost(), request.getSeat());
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (RegisterError e) {
            return new ResponseEntity<>(new RegisterError(HttpStatus.BAD_REQUEST.value(),
                    "The ticket already exists"),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteTicket{seat}")
    public ResponseEntity<?> deleteTicket(@RequestParam("seat") int seat) {
        try{
            ticketService.deleteTicket(seat);
            return new ResponseEntity<>("Ticket was successfully deleted", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
