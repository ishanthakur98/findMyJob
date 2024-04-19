package com.crazzy.coding.review.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClient;

@Configuration
public class CompanyRestClientConfig {

//    @Autowired
//    CloseableHttpClient httpClient;

    @Autowired
    Environment environment;

    @Bean
    public RestClient restClient() {

        return RestClient.builder()
                .baseUrl(environment.getProperty("company-service-base-url"))
                //.requestInterceptor(...)
                //.defaultHeader("AUTHORIZATION", fetchToken())
                //.messageConverters(...)
//                .requestFactory(clientHttpRequestFactory())
                .build();
    }

//    @Bean
//    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
//
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
//                = new HttpComponentsClientHttpRequestFactory();
//        clientHttpRequestFactory.setHttpClient(httpClient);
//        return clientHttpRequestFactory;
//    }
}