package com.crazzyy.coding.job.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
    private Review review;
}
