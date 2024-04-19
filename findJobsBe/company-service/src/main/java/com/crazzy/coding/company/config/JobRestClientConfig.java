package com.crazzy.coding.company.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class JobRestClientConfig {

//    @Autowired
//    CloseableHttpClient httpClient;

    @Autowired
    Environment environment;

    @Bean
    public org.springframework.web.client.RestClient restClient() {

        return RestClient.builder()
                .baseUrl(environment.getProperty("job-service-base-url"))
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