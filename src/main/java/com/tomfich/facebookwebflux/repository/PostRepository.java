package com.tomfich.facebookwebflux.repository;

import com.tomfich.facebookwebflux.domain.PostModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PostRepository extends ReactiveMongoRepository<PostModel, String> {

    // db.createCollection("message", { capped : true, size : 5242880, max : 50 } )

    @Tailable
    Flux<PostModel> findBy();


    Mono<PostModel> findById(String postId);
}