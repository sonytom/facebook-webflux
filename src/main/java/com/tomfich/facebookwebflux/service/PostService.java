package com.tomfich.facebookwebflux.service;

import com.tomfich.facebookwebflux.domain.PostModel;
import com.tomfich.facebookwebflux.dto.PostModelDto;
<<<<<<< HEAD
import com.tomfich.facebookwebflux.integration.UserPostIntegration;
=======
>>>>>>> main
import com.tomfich.facebookwebflux.mapper.DtoMapperToPost;
import com.tomfich.facebookwebflux.repository.PersonRepository;
import com.tomfich.facebookwebflux.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

@Data
@AllArgsConstructor
@Service
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final PersonRepository personsModelRepository;

<<<<<<< HEAD
    private final UserPostIntegration userPostIntegration;

=======
>>>>>>> main
    public Mono<PostModel> save(PostModelDto postModelDto) {
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
                // eager e lazy swtichempty
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found")))
                .log();
    }

    public Mono<Void> deletePost(String id) {
        return postRepository.findById(id)
                .flatMap(postRepository::delete).then();
    }

<<<<<<< HEAD
  //  public Mono<List<PostModel>> findByIdWithName(String id) {
  //      return personsModelRepository.findById(id)
  //              .zipWhen(persons -> getPostRepository()
  //                      .findByPeopleId(persons.getId())
  //                      .collectList())
  //              .map(tuple -> tuple.getT2().stream().toList())
  //              .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Post or Person not found")));
  //  }


     public Mono<List<PostModel>> findByIdWithName(String id) {
         return userPostIntegration.findUser(id)
                .zipWhen(persons -> getPostRepository()
                       .findByPeopleId(persons.getId())
                       .collectList())
                .map(tuple -> tuple.getT2().stream().toList())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Post or Person not found")));
     }


=======
    public Mono<List<PostModel>> findByIdWithName(String id) {
        return getPersonsModelRepository()
                .findById(id)
                .zipWhen(persons -> getPostRepository()
                        .findByPeopleId(persons.getId())
                        .collectList())
                .map(tuple -> tuple.getT2().stream().toList())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Post or Person not found")));
    }
>>>>>>> main
}
