package ru.sber.arch.restExaple.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.sber.arch.restExaple.exceptions.ResourceNotFoundException;
import ru.sber.arch.restExaple.model.Client;
import ru.sber.arch.restExaple.model.Film;


import java.util.*;
import java.util.stream.Collectors;

@Service
@Getter
public class FilmsService {
    private Set<Film> films = new HashSet<>();

    public Film addFilm(Film film) throws ResourceNotFoundException {
        Optional<Film> optionalFilm = films.stream()
                .filter(c -> Objects.equals(c.getTitle(), film.getTitle()))
                .findFirst();
        if (optionalFilm.isPresent()) {
            throw new ResourceNotFoundException(400, "Film already added.");
        }
        else {
            films.add(film);
            return film;
        }

    }

    public Film getFilmByTitle(String title) throws ResourceNotFoundException {
        Optional<Film> optionalFilm = films.stream()
                .filter(c -> c.getTitle().equals(title))
                .findFirst();

        if (optionalFilm.isPresent()) {

            return optionalFilm.get();

        } else {
            throw new ResourceNotFoundException(404, "Film not found.");
        }
    }


    public Optional<Set<Film>> getFilms(List<String> titles, String genre, String description) throws ResourceNotFoundException {
        Set<Film> films = this.getFilms();
        Set<Film> filteredFilms = films.stream()
                .filter(film -> titles == null || titles.isEmpty() || titles.contains(film.getTitle()))
                .filter(film -> genre == null || film.getGenre().equalsIgnoreCase(genre))
                .filter(film -> description == null || film.getDescription().equalsIgnoreCase(description))
                .collect(Collectors.toSet());
        if(filteredFilms.isEmpty()){
            throw new ResourceNotFoundException(404, "Films not found.");
        }
        else{
           return Optional.of(filteredFilms);
        }
    }

    public void deleteFilm(String title) throws ResourceNotFoundException {
        Optional<Film> optionalFilm = films.stream()
                .filter(c -> c.getTitle().equals(title))
                .findFirst();

        if (optionalFilm.isPresent()) {

            Film filmOb = optionalFilm.get();
            films.remove(filmOb);

        } else {
            throw new ResourceNotFoundException(404, "Film not found.");
        }
    }
}
