package com.mymusic.catalog.exceptions;

public class PlaylistNotFoundException extends ResourceNotFoundException {
    public PlaylistNotFoundException(Long id) {
        super("Playlist with " + id + " not found");
    }
}
