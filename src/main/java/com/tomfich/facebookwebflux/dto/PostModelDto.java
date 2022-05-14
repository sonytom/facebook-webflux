package com.tomfich.facebookwebflux.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostModelDto {

    @NotBlank
    @NotNull
    String peopleId;

    @NotNull
    Boolean item;
    @NotNull
    Integer likes;

    @NotNull
    @NotBlank
    String body;


}
