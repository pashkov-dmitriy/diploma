package com.mymusic.catalog.clients;

import com.mymusic.catalog.handlers.CustomResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FileStorageClient {

    @Value("${services.file-storage.name}")
    private String serviceName;
    private final DiscoveryClientUtil discoveryClientUtil;
    private final RestTemplate restTemplate;

    public FileStorageClient(DiscoveryClientUtil discoveryClientUtil, RestTemplateBuilder builder) {
        this.discoveryClientUtil = discoveryClientUtil;
        this.restTemplate = builder.errorHandler(new CustomResponseErrorHandler()).build();
    }

    //public void storeFile()
}
