package com.tomfich.facebookwebflux.controller;

import com.tomfich.facebookwebflux.domain.PostModel;
import com.tomfich.facebookwebflux.dto.PostModelDto;
import com.tomfich.facebookwebflux.service.FacebookService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Data
@AllArgsConstructor
@RestController
public class FacebookController {

    private final FacebookService facebookService;


    @GetMapping(value = "/posts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PostModel> findAllPosts() {
        return facebookService.findBy();
    }

    @PostMapping("/post")
    public Mono<PostModel> createPost(@RequestBody PostModelDto postModelDto) {
        return facebookService.save(postModelDto);
    }

}

