package com.crazzyy.coding.job.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
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
}
