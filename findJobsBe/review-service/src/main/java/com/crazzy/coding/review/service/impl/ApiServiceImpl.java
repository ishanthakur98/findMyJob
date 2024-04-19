package com.crazzy.coding.review.service.impl;

import com.crazzy.coding.review.dto.ApiRequest;
import com.crazzy.coding.review.dto.ApiResponse;
import com.crazzy.coding.review.service.ApiService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
public class ApiServiceImpl implements ApiService {

    RestClient restClient;

    @Override
    public ApiResponse callApi(ApiRequest apiRequest) {

        ApiResponse apiResponse = new ApiResponse();

        long startTime = System.currentTimeMillis();

//        RestClient restClient = clientConfig.restClient();

        ResponseEntity<String> response = null;


        try {
            if (HttpMethod.GET == apiRequest.getHttpMethod()) {
                response = restClient.get().uri(apiRequest.getEndpoint()).accept(apiRequest.getAccept()).retrieve().toEntity(String.class);
            } else if (HttpMethod.POST == apiRequest.getHttpMethod()) {
                response = restClient.post().uri(apiRequest.getEndpoint()).body(apiRequest.getBody()).contentType(apiRequest.getContentType()).accept(apiRequest.getAccept()).retrieve().toEntity(String.class);
            } else if (HttpMethod.PUT == apiRequest.getHttpMethod()) {
                response = restClient.put().uri(apiRequest.getEndpoint()).body(apiRequest.getBody()).contentType(apiRequest.getContentType()).accept(apiRequest.getAccept()).retrieve().toEntity(String.class);
            } else if (HttpMethod.DELETE == apiRequest.getHttpMethod()) {
                response = restClient.delete().uri(apiRequest.getEndpoint()).accept(apiRequest.getAccept()).retrieve().toEntity(String.class);
            }
            long timeTaken = System.currentTimeMillis() - startTime;
            apiResponse.setApiResponseCode(response.getStatusCode().value());
            apiResponse.setMessage("SUCCESS");
            apiResponse.setResponseBody(response.getBody());
            apiResponse.setApiTime(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(timeTaken)));
        } catch (Exception e) {
            log.error("Exception while sending request "+e.getStackTrace());
            throw e;
        }
        return apiResponse;
    }
}
