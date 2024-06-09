package com.mymusic.storage.controllers;

import com.mymusic.storage.exceptions.ResourceNotFoundException;
import com.mymusic.storage.lib.LimitedInputStream;
import com.mymusic.storage.services.AudioStreamLoader;
import com.mymusic.storage.utils.RangeValuesUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storage/tracks")
public class TrackStorageController {

    private final AudioStreamLoader audioStreamLoader;

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponseBody> getTrack(
            @PathVariable("id")
            Long id,
            @RequestHeader(value = "Range", required = false)
            String rangeHeader
    ) {
        try
        {
            var streamingResponse = audioStreamLoader
                    .loadPartialMediaFromStorage(id,rangeHeader);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "audio/mpeg");
            headers.add("Content-Length", String.valueOf(
                    streamingResponse.getContentLength()
            ));
            headers.add("Accept-Ranges", "bytes");
            headers.add("Content-Range",
                    String.format("bytes %d-%d/%d",
                    streamingResponse.start(), streamingResponse.end(),
                    streamingResponse.filesize()));
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).headers(headers)
                       .body(streamingResponse.body());
        }
        catch (ResourceNotFoundException e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IOException | NumberFormatException e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
