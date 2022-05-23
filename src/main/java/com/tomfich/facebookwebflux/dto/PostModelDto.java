package com.tomfich.facebookwebflux.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostModelDto {


    String peopleId;

    @NotNull
    String itemType;
    @NotNull
    Integer likes;

    @NotNull
    @NotBlank
    String body;


}
