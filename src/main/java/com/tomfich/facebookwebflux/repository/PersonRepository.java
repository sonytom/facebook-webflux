package com.tomfich.facebookwebflux.repository;

import com.tomfich.facebookwebflux.domain.PersonsModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<PersonsModel, String> {


    @Tailable
    Flux<PersonsModel> findBy() ;

}
