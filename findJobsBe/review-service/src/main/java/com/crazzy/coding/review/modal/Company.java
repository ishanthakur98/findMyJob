package com.crazzy.coding.review.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
//@ToString
@Document
public class Company {

    @Id
    private String id;

    private String name;

    private String description;

    @DBRef
    @JsonIgnore
    private List<Job> jobs;

    @DBRef
    private List<Review> review;
}
