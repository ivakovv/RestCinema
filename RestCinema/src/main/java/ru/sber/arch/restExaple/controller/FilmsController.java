package ru.sber.arch.restExaple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.arch.restExaple.exceptions.ResourceNotFoundException;
import ru.sber.arch.restExaple.model.Film;
import ru.sber.arch.restExaple.service.FilmsService;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/films")
public class FilmsController {

    @Autowired
    private FilmsService filmService;

    @PostMapping("/addFilm")
    public ResponseEntity<?> addFilm(@RequestBody Film film) {
        try{
            return new ResponseEntity<>(filmService.addFilm(film), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getFilm{title}")
    public ResponseEntity<?> getFilm(@RequestParam("title") String title) throws ResourceNotFoundException {
        try{
            return new ResponseEntity<>(filmService.getFilmByTitle(title), HttpStatus.OK);
        }
        catch(ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllFilms")
    public Set<Film> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/getFilms")
    public ResponseEntity<?> getFilms(@RequestParam(value = "title", required = false) List<String> titles,
                                      @RequestParam(value = "genre", required = false) String genre,
                                      @RequestParam(value = "description", required = false) String description)
            throws ResourceNotFoundException {
        try{
            return new ResponseEntity<>(filmService.getFilms(titles, genre, description), HttpStatus.OK);
        }
        catch(ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/deleteFilm{title}")
    public ResponseEntity<?> deleteFilm(@RequestParam("title") String title) throws ResourceNotFoundException {
        try{
            filmService.deleteFilm(title);
            return new ResponseEntity<>("The film was successfully deleted", HttpStatus.OK);
        }
        catch(ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
