package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String title;
    public int episode_id;
    public String director;
    public String producer;
    public String release_date;
    public String created;
    public String edited;
    public String url;
}
