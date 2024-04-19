package com.crazzy.coding.review.service;


import com.crazzy.coding.review.dto.ApiRequest;
import com.crazzy.coding.review.dto.ApiResponse;

public interface ApiService {

    ApiResponse callApi(ApiRequest apiRequest);
}
