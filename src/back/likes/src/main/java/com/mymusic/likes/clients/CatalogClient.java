package com.mymusic.likes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog")
public interface CatalogClient {

    @GetMapping("/tracks/{id}/exists")
    ResponseEntity<Boolean> checkIfTrackExists(@PathVariable Long id);
}
