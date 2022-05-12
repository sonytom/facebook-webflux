package com.tomfich.facebookwebflux.repository;

import com.tomfich.facebookwebflux.domain.PersonsModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<PersonsModel, String> {


}
