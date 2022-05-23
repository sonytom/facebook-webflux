package com.tomfich.facebookwebflux.controller;

import com.tomfich.facebookwebflux.domain.PostModel;
import com.tomfich.facebookwebflux.dto.PostModelDto;
import com.tomfich.facebookwebflux.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/facebook/post/v1")
public class PostController {

    private final PostService postService;

    @GetMapping(value = "/timeline", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PostModel> findAllPosts() {
        return postService.findAllPosts();
    }


    @GetMapping(value = "/posts/{postID}")
    public Mono<PostModel> findPostById(@Valid @NotNull @PathVariable(value = "postID") String postID) {
        return postService.findById(postID);
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PostModel> createPost(@RequestBody @NotNull @Valid PostModelDto postModelDto) {
        return postService.save(postModelDto);
    }

    @DeleteMapping(path = "/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePost(@PathVariable String id) {
        return postService.deletePost(id);
    }
// make this method realtime
    @GetMapping(value ="/findPostsByPerson/{id}",  produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<List<PostModel>> findPostsbyPersonId(@PathVariable(value = "id") String id) {
        return postService.findByIdWithName(id);
    }




}

