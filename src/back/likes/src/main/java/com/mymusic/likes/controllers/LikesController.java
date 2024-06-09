package com.mymusic.likes.controllers;


import com.mymusic.likes.entities.Like;
import com.mymusic.likes.services.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikesController {

    private final LikeService likeService;

    public LikesController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Like>> getUserLikes(@PathVariable long id) {
        return likeService.getUserLikes(id)
                .map(likes -> ResponseEntity.ok().body(likes))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/track/{id}")
    public ResponseEntity<?> getTrackLikes(@PathVariable long id) {
        return likeService.getTrackLikes(id)
                .map(likes -> ResponseEntity.ok().body(likes))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addLike(@RequestBody Like like) {
        return
                likeService.save(like) ?
                        new ResponseEntity<>(HttpStatus.CREATED)
                        : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLike(@RequestBody Like like) {
        return
                likeService.delete(like) ?
                        new ResponseEntity<>(HttpStatus.OK)
                        : ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
}
