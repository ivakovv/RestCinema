package ru.sber.arch.restExaple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.arch.restExaple.exceptions.ResourceNotFoundException;
import ru.sber.arch.restExaple.model.Room;
import ru.sber.arch.restExaple.service.RoomsService;

import java.util.Set;

@RestController
@RequestMapping("/rooms")
public class RoomsController {
    @Autowired
    private RoomsService roomsService;
    @GetMapping("/getAllRooms")
    public Set<Room> getRooms(){
        return roomsService.getRooms();
    }
    @GetMapping("/getRoomByNumber")
    public ResponseEntity<?> getRoomByNumber(int number){
        try{
            return new  ResponseEntity<>(roomsService.getRoom(number), HttpStatus.OK);
        } catch(ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
