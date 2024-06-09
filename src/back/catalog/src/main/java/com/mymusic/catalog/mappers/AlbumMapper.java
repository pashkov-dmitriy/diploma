package com.mymusic.catalog.mappers;

import com.mymusic.catalog.domain.dto.album.AlbumDto;
import com.mymusic.catalog.domain.dto.album.AlbumOfArtistPreviewDto;
import com.mymusic.catalog.domain.dto.artist.ArtistPreviewDto;
import com.mymusic.catalog.domain.dto.track.TrackInAlbumPreviewDto;
import com.mymusic.catalog.domain.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AlbumMapper {

    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

//    @Mapping(source = "artist.id", target = "artistId")
//    @Mapping(source = "artist.name", target = "artist")
    @Mapping(target = "coverUrl", source = "cover")
    @Mapping(target = "date", source = "releaseDate")
    @Mapping(target="owner", source = "artist")
    AlbumDto toAlbumDto(Album album);
    @Mapping(target = "coverUrl", source = "cover")
    @Mapping(target = "date", source = "releaseDate")
    AlbumOfArtistPreviewDto albumToAlbumOfArtistPreviewDto(Album album);
    List<AlbumOfArtistPreviewDto> albumsToAlbumOfArtistPreviewDtos(List<Album> albums);

    ArtistPreviewDto map(Artist artist);
    String map(AlbumType type);
    default String map(Genre genre) {
        return genre.getName();
    }

    TrackInAlbumPreviewDto map(Track track);
}
