package ru.sber.arch.restExaple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.arch.restExaple.dto.RegisterRequest;
import ru.sber.arch.restExaple.exceptions.ResourceNotFoundException;
import ru.sber.arch.restExaple.exceptions.register.RegisterError;
import ru.sber.arch.restExaple.model.Client;
import ru.sber.arch.restExaple.model.Ticket;
import ru.sber.arch.restExaple.service.StateService;

import java.util.Set;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private StateService stateService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            Ticket ticket = stateService.register(request.getClient(), request.getSession());
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (RegisterError e) {
            return new ResponseEntity<>(new RegisterError(HttpStatus.BAD_REQUEST.value(),
                    "The client with this id already exists"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getClient{id}")
    public ResponseEntity<?> getClient(@RequestParam("id") int id) throws ResourceNotFoundException {
        try{
            return new ResponseEntity<>(stateService.getClient(id), HttpStatus.OK);
        }
        catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllClients")
    public Set<Client> getClients() {
         return stateService.getClients();
    }

    @DeleteMapping("/deleteClient{id}")
    public ResponseEntity<?> deleteClient(@RequestParam("id") int id) throws ResourceNotFoundException {
        try{
            stateService.deleteClient(id);
            return new ResponseEntity<>("The client was successfully deleted", HttpStatus.OK);
        }
        catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateClient{id}")
    public ResponseEntity<?> updateClient(@RequestParam("id") int id, @RequestBody Client client) {
        try{
            stateService.updateClient(id, client);
            return new ResponseEntity<>("The client was successfully updated", HttpStatus.OK);

        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
