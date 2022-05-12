package com.tomfich.facebookwebflux.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection="posts")
@NoArgsConstructor
@Accessors(chain = true)
public class PostModel implements Serializable {

    private static final long serialVersionUID = 2612983025996993766L;


    @Id
    @Indexed(unique = true)
    String id;

    String peopleId;
    Boolean item;
    Integer likes;
    String body;


    //  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //  LocalDateTime at;
}
