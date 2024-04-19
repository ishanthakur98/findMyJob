package com.crazzy.coding.review.dto;

import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

@Data
public class ApiRequest {

    private String endpoint;

    private Object body;

    private HttpMethod httpMethod;

    private MultiValueMap<String , String> headers;

    private boolean headersPresent;

    private String service;

    private MediaType contentType;

    private MediaType accept;


}
