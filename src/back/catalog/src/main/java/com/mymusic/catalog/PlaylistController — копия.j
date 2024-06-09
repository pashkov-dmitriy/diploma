package com.mymusic.catalog.controllers;

import com.mymusic.catalog.domain.dto.playlist.*;
import com.mymusic.catalog.domain.dto.track.PlaylistTrackModifyDto;
import com.mymusic.catalog.exceptions.ResourceNotFoundException;
import com.mymusic.catalog.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

//    @GetMapping(path = "/{id}", produces = "application/json")
//    public ResponseEntity<PlaylistDto> getPlaylistById(@PathVariable Long id) {
//        return playlistService.getPlaylistDtoById(id)
//                .map(playlist -> ResponseEntity.ok()
//                        .header("Content-Type", "application/json")
//                        .body(playlist))
//                .orElse(ResponseEntity.notFound().build());
//    }
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<PlaylistDetailsDto> getPlaylistInfo(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(playlistService.getPlaylistInfoById(id));
        } catch (IllegalStateException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}/tracks")
    public ResponseEntity<PlaylistTracklistDto> getPlaylistTracklist(@PathVariable("id") Long id) {
        return ResponseEntity.ok(playlistService.getPlaylistTracklist(id));
    }

    @PostMapping()
    public ResponseEntity<PlaylistCreatedDto> createNewPlaylist(@RequestBody CreatePlaylistRequestDto newPlaylist) {
        try {
            Long playlistId = playlistService.createNewPlaylist(newPlaylist);
            return ResponseEntity.status(HttpStatus.CREATED).body(new PlaylistCreatedDto(playlistId));
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long id) {
        try {
            playlistService.removePlaylistById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/{id}/tracks")
    public ResponseEntity<?> addTracksToPlaylist(
            @RequestBody PlaylistTrackModifyDto tracks,
            @PathVariable Long id
    ) {
        try {
            PlaylistTracklistDto tracklist = playlistService.addTracksToPlaylist(id, tracks.getTrackIds());
            return ResponseEntity.ok(tracklist);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}/tracks")
    public ResponseEntity<PlaylistTracklistDto> removeTracksFromPlaylist(
            @RequestBody PlaylistTrackModifyDto tracks,
            @PathVariable("id") Long id
    ) {
        try {
            PlaylistTracklistDto tracklist = playlistService.removeTracksFromPlaylist(id, tracks.getTrackIds());
            return ResponseEntity.ok(tracklist);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @PatchMapping(path = "/{id}", produces = "application/json")
//    public ResponseEntity<PlaylistDetailsDto> updatePlaylistDetails(
//            @PathVariable("id") Long id,
//            @RequestParam UpdateDetailsRequestDto newDetails
//    ) {
//        try {
//            PlaylistDetailsDto details = playlistService.updatePlaylistDetails(id, newDetails);
//        }
//    }
}
