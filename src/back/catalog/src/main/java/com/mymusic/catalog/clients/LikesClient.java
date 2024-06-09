package com.mymusic.catalog.clients;

import lombok.NonNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name = "likes")
public interface LikesClient {

    class Like {
        @NonNull
        Long id;
        @NonNull
        Long userId;
        @NonNull Long trackId;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Like>> getUserLikes(@PathVariable long id);
}
