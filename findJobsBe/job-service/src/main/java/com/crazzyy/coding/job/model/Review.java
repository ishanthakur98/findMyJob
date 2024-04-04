package com.crazzyy.coding.job.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Review {

    @Id
    private String id;

    private String title;

    private String description;

    private String reviews;

    @DBRef
    @JsonIgnore
    private Company company;
}
