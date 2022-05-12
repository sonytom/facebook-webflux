package com.tomfich.facebookwebflux.service;

import com.tomfich.facebookwebflux.domain.PostModel;
import com.tomfich.facebookwebflux.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
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

    public Mono<PostModel> save(PostModel postModel) {
        return postRepository.save(postModel);
    }


}
