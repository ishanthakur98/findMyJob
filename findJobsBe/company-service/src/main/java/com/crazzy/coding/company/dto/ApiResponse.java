package com.crazzy.coding.company.dto;

import lombok.Data;

@Data
public class ApiResponse {

    private int apiResponseCode;

    private Object responseBody;

    private String apiTime;

    private String message;
}
