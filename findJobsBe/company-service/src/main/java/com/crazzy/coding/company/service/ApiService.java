package com.crazzy.coding.company.service;

import com.crazzy.coding.company.dto.ApiRequest;
import com.crazzy.coding.company.dto.ApiResponse;

public interface ApiService {

    ApiResponse callApi(ApiRequest apiRequest);
}
