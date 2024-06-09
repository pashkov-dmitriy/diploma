package com.mymusic.catalog.controllers;

import com.mymusic.catalog.domain.dto.album.AlbumOfArtistPreviewDto;
import com.mymusic.catalog.domain.dto.artist.ArtistDto;
import com.mymusic.catalog.services.AlbumService;
import com.mymusic.catalog.services.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
@CrossOrigin()
public class ArtistController {

    private final ArtistService artistService;
    private final AlbumService albumService;

    public ArtistController(ArtistService artistService, AlbumService albumService) {
        this.artistService = artistService;
        this.albumService = albumService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable("id") Long id) {
        return artistService.getArtistById(id)
                .map( value -> {
                    return new ResponseEntity<>(value, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @GetMapping("/popular")
//    public ResponseEntity<Iterable<Artist>> getPopularArtists() {
//        return new ResponseEntity<>(
//                repo.
//        )
//    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<ArtistDto> getArtistAlbums(@PathVariable("id") Long id) {
        return artistService.getArtistById(id)
                .map( value -> {
                    List<AlbumOfArtistPreviewDto> albums = albumService.findArtistAlbums(id);
                    value.setAlbums(albums);
                    return new ResponseEntity<>(value, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
