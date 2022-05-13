package com.tomfich.facebookwebflux.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document(collection = "posts")
@NoArgsConstructor
@Builder
@AllArgsConstructor
//@Accessors(chain = true)
public class PostModel implements Serializable {

    private static final long serialVersionUID = 2612983025996993766L;


    @Id
    @Indexed(unique = true)
    String id;

    String peopleId;
    Boolean item;
    Integer likes;
    String body;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime dateOncreatePost;
}
