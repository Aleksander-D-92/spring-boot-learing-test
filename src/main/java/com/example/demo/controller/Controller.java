package com.example.demo.controller;

import com.example.demo.dto.Actors;
import com.example.demo.dto.Movies;
import com.example.demo.service.StarWarsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class Controller {

    private final StarWarsClient client;

    @GetMapping("/star-wars/films")
    public Movies getFilms() {
        return this.client.movies();
    }

    @GetMapping("/star-wars/people")
    public Actors getPeople() {
        return this.client.actors();
    }
}
