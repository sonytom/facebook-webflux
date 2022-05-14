package com.tomfich.facebookwebflux.controller;

import com.tomfich.facebookwebflux.domain.PostModel;
import com.tomfich.facebookwebflux.dto.PostModelDto;
import com.tomfich.facebookwebflux.service.FacebookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@RestController
public class FacebookController {

    private final FacebookService facebookService;


    @GetMapping(value = "/realtime", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PostModel> findAllPosts() {
        return facebookService.findAllPosts();
    }


   @GetMapping(value = "/posts/{postID}")
   public Mono<PostModel> findPostById(@Valid @NotNull @PathVariable(value = "postID") String postID) {
        return facebookService.findById(postID);
   }


    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PostModel> createPost(@RequestBody @NotNull @Valid PostModelDto postModelDto) {
        return facebookService.save(postModelDto);
    }


    @DeleteMapping(path = "/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAnime(@PathVariable String id) {
        return facebookService.deletePost(id);
    }



}

