package com.tomfich.facebookwebflux.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomfich.facebookwebflux.stub.UserPostIntegrationStub;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserPostIntegrationTest {


    private static MockWebServer server;
    private UserPostIntegration userPostIntegration;
    private ObjectMapper objectMapper;


    @BeforeAll
    static void setupServer() throws IOException {
        server = new MockWebServer();
        server.start();
    }


    @AfterAll
    static void shutdownServer() throws IOException {
        server.close();
    }

    @BeforeEach
    void setupClass() {
        WebClient webClient = WebClient.builder()
                .baseUrl(String.format("http://localhost:%d", server.getPort()))
                .defaultHeaders(httpHeaders -> httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .build();

        objectMapper = new ObjectMapper();
        userPostIntegration = new UserPostIntegration(webClient);
    }



    @Test
    void findUser() throws JsonProcessingException {
        server.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(UserPostIntegrationStub.stubUserpostIntegration()))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        StepVerifier.create(userPostIntegration.findUser("6284f466a822387f15e88f66")).
                expectNextMatches(s -> s.equals(UserPostIntegrationStub.stubUserpostIntegration()))
                .verifyComplete();
    }
}