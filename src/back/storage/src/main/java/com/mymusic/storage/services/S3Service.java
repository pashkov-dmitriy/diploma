package com.mymusic.storage.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    public void uploadUserImage(String base64) throws IOException {
        var bytes = Base64.getDecoder().decode(base64);
        String path = "D:\\images\\";
        File file = new File(path + generateRandomFileName() + ".jpg");

        try (var writer = new BufferedWriter(new FileWriter(file.toPath().toString()))) {
            writer.write(new String(bytes));
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public byte[] getTrackObject(Long id) throws IOException {
        var file = Files.readAllBytes(Path.of("D:\\audio\\" + id + ".mp3"));
        return file;
    }

    public static String generateRandomFileName() {
        // Создание случайного UUID
        String uuid = UUID.randomUUID().toString();

        // Создание имени файла с расширением
        String fileName = uuid;

        return fileName;
    }
}
