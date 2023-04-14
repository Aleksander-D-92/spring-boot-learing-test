package com.example.demo.controller;

import com.example.demo.BaseTestClass;
import com.example.demo.dto.Actors;
import com.example.demo.dto.Movies;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllerTest2 extends BaseTestClass {

    @Test
    void getFilms1() {
        Movies movies = given().auth()
                .basic("username", "password")
                .contentType("application/json")
                .when()
                .get("http://localhost:%s/star-wars/films".formatted(port))
                .then()
                .statusCode(200)
                .extract()
                .as(Movies.class);

        assertThat(movies.getResults()).hasSize(6);
        verify(movieRepo, times(1)).saveAll(any());

    }

    @Test
    void getPeople1() {
        Actors actors = given().auth()
                .basic("username", "password")
                .contentType("application/json")
                .when()
                .get("http://localhost:%s/star-wars/people".formatted(port))
                .then()
                .statusCode(200)
                .extract()
                .as(Actors.class);

        assertThat(actors.getResults()).hasSize(10);
        verify(actorRepo, times(1)).saveAll(any());
    }

    @Test
    void getPeople1ThrowError() {
        doThrow(new NullPointerException("Mock exception")).when(actorRepo).saveAll(any());

        given().auth()
                .basic("username", "password")
                .contentType("application/json")
                .when()
                .get("http://localhost:%s/star-wars/people".formatted(port))
                .then()
                .statusCode(500);

        verify(actorRepo, times(1)).saveAll(any());
        assertThrows(NullPointerException.class, () -> actorRepo.saveAll(any()));
    }
}
