package com.crazzyy.coding.job.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
//@ToString
@Builder
@Document
public class Job {

    @Id
    private String id;

    private String name;

    private String description;

    private String minSalary;

    private String maxSalary;

    private String minExperience;

    private String maxExperience;

    private String location;

    @DBRef
//    @JsonIgnore
    private Company company;
}
