package com.example.demo.service;

import com.example.demo.domain.Actor;
import com.example.demo.domain.Movie;
import com.example.demo.repository.ActorRepo;
import com.example.demo.repository.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepositoryService {
    private final ActorRepo actorRepo;
    private final MovieRepo movieRepo;

    public Actor actorById(Long id) {
        return this.actorRepo.findById(id).orElseThrow(() -> new RuntimeException("actor not found"));
    }

    public Movie movieById(Long id) {
        return this.movieRepo.findById(id).orElseThrow(() -> new RuntimeException("actor not found"));
    }
}
