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

    public Flux<PostModel> findBy() {
        return postRepository.findBy();
    }

    public Mono<PostModel> save(PostModelDto postModelDto) {

        return Mono.just(MapperPost.mappterPostModel(postModelDto))
                .switchIfEmpty(monoResponseStatusNotFoundException())
                .map(postRepository::save)
                .flatMap(Mono::from);
    }

    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
    }


}
