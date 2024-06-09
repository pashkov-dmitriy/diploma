package com.mymusic.catalog.controllers;

import com.mymusic.catalog.domain.dto.response.BooleanResponse;
import com.mymusic.catalog.domain.dto.track.TrackInfoDto;
import com.mymusic.catalog.services.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<BooleanResponse> isTrackExists(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                trackService.isTrackExists(id)
        );
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<TrackInfoDto> getTrackInfo(@PathVariable("id") Long id) {
        return trackService.getTrackInfo(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
