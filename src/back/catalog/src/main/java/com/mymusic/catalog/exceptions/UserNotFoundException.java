package com.mymusic.catalog.exceptions;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(Long id) {
        super("User with " + id + " not found");
    }
}
