package com.example.demo.dto;

import com.example.demo.domain.Actor;
import lombok.Data;

import java.util.List;

@Data
public class Actors {

    public String count;
    public String next;
    public String previous;
    public List<Actor> results;
}
