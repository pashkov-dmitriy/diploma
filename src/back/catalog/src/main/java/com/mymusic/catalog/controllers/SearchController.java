package com.mymusic.catalog.controllers;


import com.mymusic.catalog.services.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private enum SearchType {
        ARTIST,
        ALBUM,
        PLAYLIST
    }

    private final ArtistService artistService;

    @GetMapping()
    public ResponseEntity<?> search(
            @RequestParam("query") String query,
            @RequestParam("type") int type
    ) {
       switch (type) {
           case 0 -> {
                var artists = artistService.getArtistsByName(query);
                return ResponseEntity.ok(artists);
           }
           case 1 -> {
                return ResponseEntity.accepted().build();
           }
           case 2 -> {
               return ResponseEntity.accepted().build();
           }
           default -> {
               return ResponseEntity.badRequest().build();
           }
       }
    }
}
