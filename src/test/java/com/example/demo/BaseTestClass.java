package com.example.demo;

import com.example.demo.repository.ActorRepo;
import com.example.demo.repository.MovieRepo;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@Slf4j
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.security.user.name=pesho",
                "spring.security.user.password=1234",
                "spring.security.user.roles=ADMIN"
        }
)
@ActiveProfiles(profiles = "test")
public abstract class BaseTestClass {
    @LocalServerPort
    protected int port;
    @SpyBean
    public MovieRepo movieRepo;
    @SpyBean
    public ActorRepo actorRepo;

    protected static WireMockServer wireMockServer;

    @BeforeAll
    static void startWireMock() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8888));
        wireMockServer.start();
        log.info("\n{}\nWiremock server started on: {}\n{}", "*".repeat(70), wireMockServer.baseUrl(), "*".repeat(70));
        initWireMockStubs();
        log.info("\n{}\nWiremock stubs initialized\n{}", "*".repeat(70), "*".repeat(70));
    }

    @AfterAll
    static void stopWireMock() {
        wireMockServer.stop();
        log.info("\n{}\nWiremock server shut down\n{}", "*".repeat(70), "*".repeat(70));
    }

    private static void initWireMockStubs() {
        byte[] filmsResponse = TestFileUtil.readFileAsString("src/test/resources/film-response.json");
        wireMockServer.stubFor(WireMock.get("/films")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(filmsResponse)));

        byte[] peopleResponse = TestFileUtil.readFileAsString("src/test/resources/people-response.json");
        wireMockServer.stubFor(WireMock.get("/people")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(peopleResponse)));

    }
}
