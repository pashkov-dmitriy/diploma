package com.mymusic.catalog.mappers;

import com.mymusic.catalog.domain.dto.album.AlbumOfArtistPreviewDto;
import com.mymusic.catalog.domain.dto.track.TrackInAlbumPreviewDto;
import com.mymusic.catalog.domain.dto.track.TrackPreviewInPlaylistDto;
import com.mymusic.catalog.domain.entities.Track;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TrackMapper {

    TrackMapper INSTANCE = Mappers.getMapper(TrackMapper.class);

    TrackInAlbumPreviewDto toTrackInAlbumPreviewDto(Track track);
    TrackPreviewInPlaylistDto toTrackPreviewInPlaylistDto(Track track);
    List<AlbumOfArtistPreviewDto> toAlbumOfArtistPreviewDtoList(List<Track> tracks);
}
