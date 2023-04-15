package com.example.demo.controller;

import com.example.demo.domain.Actor;
import com.example.demo.domain.Movie;
import com.example.demo.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DbController {

    private final RepositoryService repositoryService;

    @GetMapping("/db/people/{id}")
    public Actor actorById(@PathVariable Long id) {
        return this.repositoryService.actorById(id);
    }

    @GetMapping("/db/film/{id}")
    public Movie movieById(@PathVariable Long id) {
        return this.repositoryService.movieById(id);
    }
}
