package ru.sber.arch.restExaple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.arch.restExaple.exceptions.ResourceNotFoundException;
import ru.sber.arch.restExaple.model.Session;
import ru.sber.arch.restExaple.service.SessionService;

import java.util.Set;

@RestController
@RequestMapping("/sessions")
public class SessionsConroller {

    @Autowired
    private SessionService sessionService;

    @GetMapping("/getAllSessions")
    public Set<Session> getAllSessions() {
        return sessionService.getSessions();
    }
    @GetMapping("/getSessionsByDate")
    public ResponseEntity<?> getSessionsByDate(@RequestParam("date") String date) {
        try{
            return new ResponseEntity<>(sessionService.getSessions(date), HttpStatus.OK);
        } catch(ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
