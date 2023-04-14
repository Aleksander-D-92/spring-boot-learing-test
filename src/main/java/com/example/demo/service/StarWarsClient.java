package com.example.demo.service;

import com.example.demo.dto.Actors;
import com.example.demo.dto.Movies;
import com.example.demo.repository.ActorRepo;
import com.example.demo.repository.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class StarWarsClient {

    private final RestTemplate rt;
    private final ActorRepo actorRepo;
    private final MovieRepo movieRepo;

    @Value("${integration.star-wars-api}")
    private String baseUrl;

    public Movies movies() {
        var resp = this.rt.getForObject(baseUrl + "/films", Movies.class);
        movieRepo.saveAll(resp.getResults());
        return resp;
    }

    public Actors actors() {
        var resp = this.rt.getForObject(baseUrl + "/people", Actors.class);
        actorRepo.saveAll(resp.getResults());
        return resp;
    }
}
