package com.mymusic.catalog.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "playlist_track")
public class PlaylistTrack {
    @EmbeddedId
    private PlaylistTrackId id;

    @MapsId("playlistPlaylistId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "playlist_playlist_id", nullable = false)
    private Playlist playlistPlaylist;

    @MapsId("trackTrackId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "track_track_id", nullable = false)
    private Track trackTrack;

}