package com.tomfich.facebookwebflux.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "persons")
@NoArgsConstructor
@Accessors(chain = true)

public class PersonsModel implements Serializable {
    private static final long serialVersionUID = 2612983025996993766L;


    @Id
    @Indexed(unique = true)
    String id;
    String name;


}
