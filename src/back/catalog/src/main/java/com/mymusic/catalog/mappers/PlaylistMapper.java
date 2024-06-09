package com.mymusic.catalog.mappers;

import com.mymusic.catalog.domain.dto.playlist.PlaylistDetailsDto;
import com.mymusic.catalog.domain.dto.playlist.PlaylistTracklistDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.mymusic.catalog.domain.dto.playlist.PlaylistDto;
import com.mymusic.catalog.domain.dto.track.TrackPreviewInPlaylistDto;
import com.mymusic.catalog.domain.entities.Playlist;
import com.mymusic.catalog.domain.entities.Track;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    PlaylistMapper INSTANCE = Mappers.getMapper(PlaylistMapper.class);

    PlaylistDto playlistToPlaylistDto(Playlist playlist);
    @Mapping(target = "owner", ignore = true)
    PlaylistDetailsDto playlistToPlaylistInfoDto(Playlist playlist);
    PlaylistTracklistDto toPlaylistTracklistDto(Playlist playlist);
    List<TrackPreviewInPlaylistDto> map(Set<Track> tracks);
}
