package com.tomfich.facebookwebflux.service;

import com.tomfich.facebookwebflux.domain.PostModel;
import com.tomfich.facebookwebflux.dto.PostModelDto;
import com.tomfich.facebookwebflux.mapper.MapperPost;
import com.tomfich.facebookwebflux.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@Service
public class FacebookService {

    private final PostRepository postRepository;

    public Mono<PostModel> save(PostModelDto postModelDto) {

        return Mono.just(postModelDto)
                //.switchIfEmpty(monoResponseStatusNotFoundException())
                .onErrorResume(throwable -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not Found")))
                .map(MapperPost::mappterPostModel)
                //  .map(postModel -> postRepository.findById(postModel.getPeopleId()))
                // .onErrorReturn(monoResponseStatusNotFoundException())
                //  .flatMap(Mono::from)
                .map(postRepository::save)
                .flatMap(Mono::from).log();
    }


    public Flux<PostModel> findAllPosts() {
        return postRepository.findBy();
    }


    public Mono<PostModel> findById(String postID) {
        return postRepository.findById(postID)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found")))
                .log();

    }

    public Mono<Void> deletePost(String id) {
        return postRepository.findById(id)
                .flatMap(postRepository::delete).then();
    }

    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
    }


}
