package com.mymusic.likes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-management")
public interface UserManagerClient {

    @GetMapping("/users/{id}/exists")
    ResponseEntity<Boolean> checkIfUserExists(@PathVariable Long id);
}
