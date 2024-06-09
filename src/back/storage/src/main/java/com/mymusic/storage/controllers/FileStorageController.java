package com.mymusic.storage.controllers;

import com.mymusic.storage.services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileStorageController {

    private final S3Service s3Service;

    @PostMapping("/storage/users")
    public ResponseEntity<?> uploadUserImage(@RequestBody String userImage) {
        try {
            s3Service.uploadUserImage(userImage);
            return ResponseEntity.ok().build();
        }
        catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
