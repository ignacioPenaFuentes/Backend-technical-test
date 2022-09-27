package com.nachoPena.technicaltest;

import org.openapitools.client.ApiClient;
import org.openapitools.client.api.DefaultApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ProductApiIntegrationConfig {

    @Value("${client.port}")
    private String clientPort;

    @Bean
    public DefaultApi defaultApi() {
        return new DefaultApi(apiClient(restTemplate(new RestTemplateBuilder())));
    }

    @Bean
    public ApiClient apiClient(RestTemplate restTemplate) {
        ApiClient apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(clientPort);
        return apiClient;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder)
    {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(5500))
                .setReadTimeout(Duration.ofMillis(5500))
                .build();
    }
}
