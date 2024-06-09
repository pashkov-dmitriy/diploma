package com.mymusic.storage.clients;

import com.mymusic.storage.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CatalogClient {

    public boolean isTrackExists(Long trackId) throws ResourceNotFoundException {
        return true;
    }
}
