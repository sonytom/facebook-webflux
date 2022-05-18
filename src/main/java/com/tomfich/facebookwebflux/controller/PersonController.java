package com.tomfich.facebookwebflux.controller;


import com.tomfich.facebookwebflux.domain.PersonsModel;
import com.tomfich.facebookwebflux.service.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/facebook/person/v1")
public class PersonController {

    private final PersonService personService;

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonsModel> createPerson(@RequestBody @NotNull @Valid PersonsModel personsModel) {
        return personService.savePerson(personsModel);
    }


    @GetMapping("/allpersons")
    public Flux<PersonsModel> findAllPosts() {
        return personService.findAllPersons();
    }


}
