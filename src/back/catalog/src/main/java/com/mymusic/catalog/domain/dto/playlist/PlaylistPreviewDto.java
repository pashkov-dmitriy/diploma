package com.mymusic.catalog.domain.dto.playlist;

import com.mymusic.catalog.domain.entities.Playlist;

import java.io.Serializable;

/**
 * DTO for {@link Playlist}
 */
public record PlaylistPreviewDto(
        Long id,
        String name,
        String coverUrl
) implements Serializable {
}