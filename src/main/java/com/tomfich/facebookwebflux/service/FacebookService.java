package com.tomfich.facebookwebflux.service;

import com.tomfich.facebookwebflux.domain.PostModel;
import com.tomfich.facebookwebflux.dto.PostModelDto;
import com.tomfich.facebookwebflux.mapper.DtoMapperToPost;

import com.tomfich.facebookwebflux.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@Service
@Slf4j
public class FacebookService {

    private final PostRepository postRepository;

    public Mono<PostModel> save(PostModelDto postModelDto) {


        // verificar no banco se está com unique
        return Mono.just(postModelDto)
                .onErrorResume(throwable -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not Found")))
                .map(DtoMapperToPost::dtoMappterToPostModel)
                .map(postRepository::save)
                .flatMap(postModelMono -> postModelMono.onErrorResume((Throwable e) -> {
                    log.error(e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage()));
                }));
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


}
