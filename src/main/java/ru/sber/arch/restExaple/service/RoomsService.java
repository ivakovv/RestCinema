package ru.sber.arch.restExaple.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.sber.arch.restExaple.exceptions.ResourceNotFoundException;
import ru.sber.arch.restExaple.model.Room;
import ru.sber.arch.restExaple.model.Ticket;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Getter
public class RoomsService {
    private Set<Room> rooms = new HashSet<>();

    public Room getRoom(int number) throws ResourceNotFoundException {
        Optional<Room> optionalRoom = rooms.stream()
                .filter(r -> r.getNumber() == number)
                .findFirst();

        if (optionalRoom.isPresent()) {
            return optionalRoom.get();
        } else {
            throw new ResourceNotFoundException(404, "Room not found.");
        }
    }
}
