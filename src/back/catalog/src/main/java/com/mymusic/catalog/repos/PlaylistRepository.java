package com.mymusic.catalog.repos;

import com.mymusic.catalog.domain.dto.track.TrackInAlbumPreviewDto;
import com.mymusic.catalog.domain.dto.track.TrackPreviewInPlaylistDto;
import com.mymusic.catalog.domain.entities.Playlist;
import com.mymusic.catalog.domain.entities.Track;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    @Query("select p.tracks from Playlist p where p.id = :playlistId")
    Optional<Set<Track>> findTracksByPlaylistId(@Param("playlistId") Long playlistId);

    Optional<Playlist> findPlaylistById(Long playlistId);

    List<Playlist> getPlaylistsByUserId(Long id);
}
