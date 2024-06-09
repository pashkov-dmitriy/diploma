package com.mymusic.catalog.services;

import com.mymusic.catalog.clients.FileStorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final FileStorageClient client;

//    private File decryptBase64(String base64) {
//        byte[] encodedBytes = Base64.getEncoder().encode(base64.getBytes(StandardCharsets.UTF_8));
//
//
//    }
}
