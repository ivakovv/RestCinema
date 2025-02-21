package ru.sber.arch.restExaple.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.sber.arch.restExaple.exceptions.ResourceNotFoundException;
import ru.sber.arch.restExaple.model.Film;
import ru.sber.arch.restExaple.model.Session;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Getter
public class SessionService {
    private Set<Session> sessions = new HashSet<>();
    public Set<Session> getSessions(String date) throws ResourceNotFoundException {
        Set<Session> sessions = this.getSessions();
        Set<Session> filteredSessions = sessions.stream()
                .filter(session -> date.equals(session.getDate())).collect(Collectors.toSet());
        if(filteredSessions.isEmpty()) {
            throw new ResourceNotFoundException(404, "Sessions not found.");
        }
        else {
            return filteredSessions;
        }
    }

}
