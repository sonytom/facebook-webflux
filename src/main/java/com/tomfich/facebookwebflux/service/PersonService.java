package com.tomfich.facebookwebflux.service;

import com.tomfich.facebookwebflux.domain.PersonsModel;
import com.tomfich.facebookwebflux.domain.PostModel;
import com.tomfich.facebookwebflux.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@Service
@Slf4j
public class PersonService {

    private final PersonRepository personsModelRepository;

    public Mono<PersonsModel> savePerson(PersonsModel personsModel) {
        return personsModelRepository.save(personsModel);
    }


    public Flux<PersonsModel> findAllPersons() {
    return  personsModelRepository.findBy();
    }

}
