package com.mymusic.catalog.domain.dto.playlist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mymusic.catalog.domain.dto.track.TrackPreviewInPlaylistDto;
import com.mymusic.catalog.domain.entities.Playlist;
import com.mymusic.catalog.domain.dto.track.TrackInAlbumPreviewDto;
import com.mymusic.catalog.domain.entities.Track;
import com.mymusic.catalog.domain.interfaces.ItemCollection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link Playlist}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto implements Serializable, ItemCollection<TrackPreviewInPlaylistDto> {
    Long id;
    Long userId;
    String name;
    String description;
    String coverUrl;
    @JsonIgnore
    List<TrackPreviewInPlaylistDto> tracks;

    @Override
    public Collection<TrackPreviewInPlaylistDto> getItems() {
        return tracks;
    }
}