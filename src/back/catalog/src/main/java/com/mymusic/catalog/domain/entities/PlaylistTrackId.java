package com.mymusic.catalog.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.Objects;

@Data
@Embeddable
public class PlaylistTrackId implements java.io.Serializable {
    private static final long serialVersionUID = -7441178758920115268L;
    @Column(name = "playlist_playlist_id", nullable = false)
    private Long playlistPlaylistId;

    @Column(name = "track_track_id", nullable = false)
    private Long trackTrackId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlaylistTrackId entity = (PlaylistTrackId) o;
        return Objects.equals(this.trackTrackId, entity.trackTrackId) &&
                Objects.equals(this.playlistPlaylistId, entity.playlistPlaylistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackTrackId, playlistPlaylistId);
    }

}