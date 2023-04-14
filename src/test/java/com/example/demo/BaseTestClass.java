package com.example.demo;

import com.example.demo.repository.ActorRepo;
import com.example.demo.repository.MovieRepo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.security.user.name=pesho",
                "spring.security.user.password=1234",
                "spring.security.user.roles=ADMIN"
        }
)
public abstract class BaseTestClass {
    @LocalServerPort
    protected int port;
    @SpyBean
    public MovieRepo movieRepo;
    @SpyBean
    public ActorRepo actorRepo;
}
