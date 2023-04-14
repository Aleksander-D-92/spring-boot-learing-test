package com.example.demo.dto;

import com.example.demo.domain.Movie;
import lombok.Data;

import java.util.List;

@Data
public class Movies {

    private String count;
    private String next;
    private String previous;
    private List<Movie> results;
}
