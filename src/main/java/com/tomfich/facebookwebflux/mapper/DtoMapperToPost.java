package com.tomfich.facebookwebflux.mapper;

import com.tomfich.facebookwebflux.domain.PostModel;
import com.tomfich.facebookwebflux.dto.PostModelDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DtoMapperToPost {

    public static PostModel dtoMappterToPostModel(PostModelDto postModelDto) {
        LocalDateTime timeNow = LocalDateTime.now(ZoneId.of("UTC"));
        return Optional.ofNullable(postModelDto)
                .map(dtoModel -> PostModel.builder()
                        .peopleId(dtoModel.getPeopleId())
                        .itemType(dtoModel.getItemType())
                        .likes(dtoModel.getLikes())
                        .body(dtoModel.getBody())
                        .dateOncreatePost(timeNow)
                        .build())
                .orElse(null);
    }
}
