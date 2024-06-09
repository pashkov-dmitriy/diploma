package com.mymusic.catalog.mappers;

import com.mymusic.catalog.domain.dto.album.AlbumOfArtistPreviewDto;
import com.mymusic.catalog.domain.dto.artist.ArtistDto;
import com.mymusic.catalog.domain.dto.artist.ArtistPreviewDto;
import com.mymusic.catalog.domain.dto.artist.ArtistWithRefDto;
import com.mymusic.catalog.domain.entities.Album;
import com.mymusic.catalog.domain.entities.Artist;
import com.mymusic.catalog.domain.entities.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArtistMapper {
    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);


    ArtistDto artistToArtistDto(Artist artist);
    ArtistPreviewDto artistToArtistPreviewDto(Artist artist);
    ArtistWithRefDto artistToArtistWithRefDto(Artist artist);
    String map(Genre genre);
    AlbumOfArtistPreviewDto map(Album album);
}
