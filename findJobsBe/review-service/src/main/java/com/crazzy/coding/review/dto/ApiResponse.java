package com.crazzy.coding.review.dto;

import lombok.Data;

@Data
public class ApiResponse {

    private int apiResponseCode;

    private String responseBody;

    private String apiTime;

    private String message;
}
