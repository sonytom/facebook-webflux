package com.tomfich.facebookwebflux.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostModelDto {


    String peopleId;
    Boolean item;
    Integer likes;
    String body;


}
