package com.crazzyy.coding.job.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Job {

    private long id;

    private String name;

    private String description;

    private String minSalary;

    private String maxSalary;

    private String minExperience;

    private String maxExperience;

    private String location;
}
