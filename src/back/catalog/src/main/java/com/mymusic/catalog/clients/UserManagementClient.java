package com.mymusic.catalog.clients;

import com.mymusic.catalog.domain.dto.owner.OwnerPreviewDto;
import com.mymusic.catalog.domain.dto.response.BooleanResponse;
import com.mymusic.catalog.exceptions.ResourceNotFoundException;
import com.mymusic.catalog.exceptions.UserNotFoundException;
import com.mymusic.catalog.handlers.CustomResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class UserManagementClient {

    @Value("${services.user-management.name}")
    private String userManagementName;
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    public UserManagementClient(RestTemplateBuilder builder, DiscoveryClient client) {
        this.discoveryClient = client;
        restTemplate = builder.errorHandler(new CustomResponseErrorHandler()).build();
    }

    private String getUserManagementUri() throws IllegalStateException {
        List<ServiceInstance> instances = discoveryClient.getInstances(userManagementName);

        if (instances == null) {
            throw new IllegalStateException("User service is not available");
        }

        ServiceInstance instance = instances.getFirst();
        return instance.getUri().toString();
    }

    public BooleanResponse isUserExists(Long userId) throws IllegalStateException{
        String urlTemplate = getUserManagementUri() + "/internal/users/{id}/exists";

       try {
            return restTemplate
                .getForEntity(urlTemplate, BooleanResponse.class, userId)
                .getBody();
       }
       catch (ResourceNotFoundException e) {
            throw new UserNotFoundException(userId);
       }
       catch (Exception e) {
           throw new IllegalStateException();
       }
    }

    public OwnerPreviewDto getUserPreview(Long id) throws UserNotFoundException, IllegalStateException {
        String urlTemplate = getUserManagementUri() + "/internal/users/{id}/preview";

        try {
            return restTemplate
                    .getForEntity(urlTemplate, OwnerPreviewDto.class, id)
                    .getBody();
        }
        catch (ResourceNotFoundException e) {
            throw new UserNotFoundException(id);
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}
