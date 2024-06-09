package com.mymusic.catalog.clients;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscoveryClientUtil {
    private final DiscoveryClient discoveryClient;

    protected DiscoveryClientUtil(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    private String getServiceUri(String serviceName) throws IllegalStateException {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);

        if (instances == null) {
            throw new IllegalStateException("User service is not available");
        }

        ServiceInstance instance = instances.getFirst();
        return instance.getUri().toString();
    }
}
