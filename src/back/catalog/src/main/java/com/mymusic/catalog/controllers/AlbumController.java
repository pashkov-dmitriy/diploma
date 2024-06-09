package com.mymusic.catalog.controllers;

import com.mymusic.catalog.domain.dto.album.AlbumDto;
import com.mymusic.catalog.mappers.AlbumMapper;
import com.mymusic.catalog.services.AlbumService;
import com.mymusic.catalog.services.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
@CrossOrigin()
public class AlbumController {

    private final AlbumService albumService;
    private final TrackService trackService;

    public AlbumController(AlbumService albumService, TrackService trackService) {
        this.albumService = albumService;
        this.trackService = trackService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> getAlbumById(@PathVariable("id") Long id) {
        return albumService.findAlbumById(id)
                .map(value -> {
                    AlbumDto albumDto = AlbumMapper.INSTANCE.toAlbumDto(value);
                    albumDto.setTracks(trackService.getTracksInAlbum(value.getId()));
                    return new ResponseEntity<>(albumDto, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
