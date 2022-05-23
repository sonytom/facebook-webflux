package com.tomfich.facebookwebflux.integration;


import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserPostIntegration {


    WebClient webClient;
    
    private static final String USER = "http://localhost:8080/facebook/user/v1/person/";



    public Mono<UserIntegrationResponse> findUser(String userID) {
        return WebClient.create().get()
                .uri(USER+userID)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserIntegrationResponse.class);
    }


}
