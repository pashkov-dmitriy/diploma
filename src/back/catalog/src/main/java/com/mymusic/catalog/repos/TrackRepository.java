package com.mymusic.catalog.repos;

import com.mymusic.catalog.domain.dto.track.TrackPreviewInPlaylistDto;
import com.mymusic.catalog.domain.entities.Track;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> findTracksByAlbumId(Long albumId);

    @Query("select t from Track t join t.playlists p where p.id = :playlistId")
    List<Track> findTracksByPlaylistId(Long playlistId);

    @Query("select new com.mymusic.catalog.domain.dto.track.TrackPreviewInPlaylistDto(t.id, t.name, t.duration, a.cover)" +
            "from Track t join t.album a join t.playlists p where p.id = :id")
    Optional<List<TrackPreviewInPlaylistDto>> findTracksWithCoversById(Long id);

    boolean existsById(Long id);
}
