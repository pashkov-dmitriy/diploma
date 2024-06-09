package com.mymusic.catalog.services;

import com.mymusic.catalog.clients.UserManagementClient;
import com.mymusic.catalog.domain.dto.response.BooleanResponse;
import com.mymusic.catalog.domain.dto.track.TrackInAlbumPreviewDto;
import com.mymusic.catalog.domain.dto.track.TrackInfoDto;
import com.mymusic.catalog.domain.dto.track.TrackPreviewInPlaylistDto;
import com.mymusic.catalog.domain.entities.Track;
import com.mymusic.catalog.mappers.TrackMapper;
import com.mymusic.catalog.repos.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;

    public Optional<Track> findTrackById(Long id) {
        return trackRepository.findById(id);
    }

    public Optional<List<TrackPreviewInPlaylistDto>> getTracksWithCoverInPlaylist(Long playlistId) {
        return trackRepository.findTracksWithCoversById(playlistId);
    }

    public List<TrackInAlbumPreviewDto> getTracksInAlbum(Long albumId) {
        return trackRepository.findTracksByAlbumId(albumId)
                .stream().map(TrackMapper.INSTANCE::toTrackInAlbumPreviewDto)
                .toList();
    }

    public List<Track> getTracksByIds(Iterable<Long> ids) {
        return trackRepository.findAllById(ids);
    }

    public BooleanResponse isTrackExists(Long id) {
        return new BooleanResponse(
                trackRepository.existsById(id)
        );
    }

    public Optional<TrackInfoDto> getTrackInfo(Long id) {
        return trackRepository.findById(id).map(t -> {
            TrackInfoDto dto = new TrackInfoDto();
            dto.setId(t.getId());
            dto.setName(t.getName());
            dto.setArtist(t.getAlbum().getArtist().getName());
            dto.setCoverUrl(t.getAlbum().getCover());

            return dto;
        });
    }
}
