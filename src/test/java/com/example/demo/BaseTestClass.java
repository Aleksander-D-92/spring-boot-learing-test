package com.example.demo;

import com.example.demo.repository.ActorRepo;
import com.example.demo.repository.MovieRepo;
import com.example.demo.service.RepositoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static com.example.demo.TestFileUtil.fileToByteArray;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;

@Slf4j
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.security.user.name=pesho",
                "spring.security.user.password=1234",
                "spring.security.user.roles=ADMIN",
                "spring.profiles.active=test" // can use this or @ActiveProfiles(profiles = "test")
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
    @MockBean
    public RepositoryService repositoryService;
    @Autowired
    public ObjectMapper objectMapper;

    protected static WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8888));

    @BeforeAll
    static void startWireMock() {
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
        buildWireMockGetStub("src/test/resources/film-response.json", "/films");
        buildWireMockGetStub("src/test/resources/people-response.json", "/people");
    }

    private static void buildWireMockGetStub(String classPath, String route) {
        byte[] response = fileToByteArray(classPath);
        wireMockServer.stubFor(WireMock.get(route)
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
    }

    private static void buildWireMockPostStub(String classPath, String route) {
        byte[] response = fileToByteArray(classPath);
        wireMockServer.stubFor(WireMock.post(route)
                .withRequestBody(equalToJson("{\"name\":  \"pesho\"}"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
    }
}
